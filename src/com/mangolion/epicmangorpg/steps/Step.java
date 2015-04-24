package com.mangolion.epicmangorpg.steps;

import java.util.Random;

import javax.swing.JOptionPane;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.components.StatBuff;
import com.mangolion.epicmangorpg.components.Tick;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgParrySuccess;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.statuses.Status;

public abstract class Step implements Cloneable, StatBuff {
	public ActionType type;
	public Skill parent;
	public String name, desc;
	public Random rand = new Random();
	public float chanceMiss = 1, chanceDodge  = 1, chanceParry = 1, chanceBlock =1;
	public float prof = 0, timeLoad, timeExecute, timeCooldown, hpCost = 0, balCost = 0,
			mpCost = 0, stamCost = 0, value = 0, chanceStatus = 0, cp = 0, dmgBase = 0;
	protected float  dmgPercent = 0;
	public Element element;
	public Status status;

	public boolean cancelfromStun = true, strBased = true, intBased = false;;
	
	public void init(){
		
	}
	
	public Step setElement(Element element){
		this.element = element;
		return this;
	}
	
	public Step setStatus(Status status, float chance){
		this.status = status;
		chanceStatus = chance;
		return this;
	}
	
	public float getDamage(){
		float skilDmg = 0;
		for (Skill skill: getCharacter().skills)
			if (skill.type == ActionType.WeaponMastery && skill.checkWeapon(getCharacter().weapon))
				skilDmg = skill.getTotalDamagePercent()+1;
		if (strBased)
			return ((getCharacter().weapon.baseDamage + dmgBase + getCharacter().getStrDamage())*getDmgPercent()*getCharacter().weapon.meleeDamageModifier)*skilDmg;
		else
			return ((getCharacter().weapon.baseMagicDmg + dmgBase + getCharacter().getIntDamage())*getDmgPercent()*getCharacter().weapon.baseMagicDmgMod)*skilDmg;
	}
	
	public void addProf(Proficiency p){
		float gained =  p.type/100f*(2f - prof)/2;
		if (p.target.isDead){
			gained *= 2;
			if (p.source == CharacterPlayer.instance)
			if (Game.getInstance().nextFloor)
				Game.getInstance().floorPercent+= p.type*50;
			else if(Game.getInstance().lastFloor)
				Game.getInstance().floorPercent -= p.type*100;
		}
		if (getCharacter() == CharacterPlayer.instance)
			LogMsg.appendLog(", \n" + name +" gained " +gained*100 + "% proficiency");
		prof += gained;
		for (Skill skill: getCharacter().skills)
			if (skill.type == ActionType.WeaponMastery && skill.checkWeapon(getCharacter().weapon)){
				skill.prof += gained/2;
				LogMsg.appendLog(", \n" + skill.name +" gained " + gained/2*100*(4f - prof)/4 + "% proficiency");
			}
	}
	
	public void damage(Character target, float subtract){
		float dmg = getDamage() - subtract;
		if (dmg <= 0)
			dmg = 1;
			target.setDamage(getCharacter(), dmg);
		if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
			target.addStatus(getStatus());
		
		addProf(new Proficiency(getCharacter(), target));
		if (target.isDead)
			cancel();
	}
	
	private Status getStatus() {
		Status newStatus =status.copy();
		newStatus.character = getCharacter().getTarget();
		newStatus.time *= (prof + 1);
		return newStatus;
	}

	public void damage(Character target){
		float eleMult = -2;
		for (Element element: target.elements){
			if (this.element != null)
				eleMult = (eleMult == -2)? element.type.calculate(this.element.type): (eleMult + element.type.calculate(this.element.type))/2;
				else
					eleMult = (eleMult == -2)? element.type.calculate(getCharacter().elements.getFirst().type): (eleMult + element.type.calculate(getCharacter().elements.getFirst().type))/2;
		}
		System.out.println(getCharacter().name + " " + eleMult);
		if (eleMult == -2)
			eleMult = 1;
		float dmg = getDamage()*eleMult;
			target.setDamage(getCharacter(), dmg);
			if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
				target.addStatus(getStatus());
			addProf(new Proficiency(getCharacter(), target));
	}
	
	public Step setChances(float block, float parry, float miss) {
		chanceBlock = block;
		chanceParry = parry;
		chanceMiss = miss;
		return this;
	}
	
	public boolean isCancelfromStun() {
		return cancelfromStun;
	}

	public void setCancelfromStun(boolean cancelfromStun) {
		this.cancelfromStun = cancelfromStun;
	}

	boolean customTime = false;
	public boolean isCustomTime() {
		return customTime;
	}

	public void setCustomTime(boolean customTime) {
		this.customTime = customTime;
	}

	public Msg msgLoad, msgExecute, msgCooldown, msgMiss = new MsgSlashMiss()
		, msgParry = new MsgParrySuccess();
	public float customExecutionTime = -1;

	public Character getCharacter() {
		return parent.character;
	}

	public Step(Skill parent, String name,  String desc, float timeLoad, float timeExecute,
			float timeCooldown) {
		this.parent = parent;
		this.name = name;
		this.timeLoad = timeLoad;
		this.timeExecute = timeExecute;
		this.timeCooldown = timeCooldown;
		this.desc = desc;
		init();
	}

	public Step(Skill parent, String name, String desc,  ActionType type, float timeLoad,
			float timeExecute, float timeCooldown, float hpCost, float mpCost, float balCost,
			float stamCost, float dmgPercent) {
		this.parent = parent;
		this.name = name;
		this.timeLoad = timeLoad;
		this.timeExecute = timeExecute;
		this.timeCooldown = timeCooldown;
		this.hpCost = hpCost;
		this.mpCost = mpCost;
		this.stamCost = stamCost;
		this.balCost  = balCost;
		this.type = type;
		this.dmgPercent = dmgPercent;
		this.desc = desc;
		init();
	}

	public void load() {
		// Utility.narrate(character.name + " is loading " + step.name
		// + ",  execution in " + time + " seconds");
		LogMsg.addLog(new LogMsg(getCharacter().name + " is loading " + name
				 + ": " + getLoadTime() + " sec", Game.getInstance().timePassed));
		if (msgLoad == null)
			StylePainter.append(new StyleSegment(StylePainter.NAME,
					getCharacter().name), new StyleSegment(StylePainter.NULL,
					" is loading "),
					new StyleSegment(StylePainter.SKILL, name),
					new StyleSegment(StylePainter.NULL, ": "),
					new StyleSegment(StylePainter.NUMBER, "" + getLoadTime()),
					new StyleSegment(StylePainter.NULL, " seconds\n"));
		else
			StylePainter.append(msgLoad.getMessage(parent.character,
					parent.character.getTarget(), getLoadTime()));
		getCharacter().useStamina(stamCost/2);
		getCharacter().useMana(mpCost/2);
	}

	public void execute() {
		execute(getCharacter().getTarget());
	}
	
	public void execute(Character target){
		execute(target, getExecutionTime());
	}
	
	public void execute(Character target, float time) {
		getCharacter().useStamina(stamCost/2);
		getCharacter().useMana(mpCost/2);
		// Utility.narrate(getCharacter().name + " is executing " + step.name
		// + ",  execution time is " + time + " seconds");
		LogMsg.addLog(new LogMsg(getCharacter().name + " is executing " + name
				 + ": " +time + " sec", Game.getInstance().timePassed));
		if (msgExecute == null)
			StylePainter.append(new StyleSegment(StylePainter.NAME,
					getCharacter().name), new StyleSegment(StylePainter.NULL,
					" is executing "), new StyleSegment(StylePainter.SKILL,
					name),
					new StyleSegment(StylePainter.NULL, ",  duration:  "),
					new StyleSegment(StylePainter.NUMBER, ""
							+ time), new StyleSegment(
							StylePainter.NULL, " seconds\n"));
		else
			StylePainter.append(msgExecute.getMessage(parent.character,
					target, getExecutionTime()));
	}

	public void cooldown() {
		/*
		 * Utility.narrate(character.name + "'s  " + step.name +
		 * " has completed and has left an opening for " + time + " seconds");
		 */
		LogMsg.addLog(new LogMsg(getCharacter().name + "'s  " + name
				+ " is on cooldown: "
				+ getCooldownTime() + " sec", Game.getInstance().timePassed));
		if (msgCooldown == null)
			StylePainter.append(new StyleSegment(StylePainter.NAME,
					parent.character.name), new StyleSegment(StylePainter.NULL,
					"'s "), new StyleSegment(StylePainter.SKILL, name),
					new StyleSegment(StylePainter.NULL,
							"  has completed and has left an opening for "),
					new StyleSegment(StylePainter.NUMBER, ""
							+ getCooldownTime()), new StyleSegment(
							StylePainter.NULL, " seconds\n"));
		else
			StylePainter.append(msgCooldown.getMessage(parent.character,
					parent.character.getTarget(), getCooldownTime()));
	}

	public float getLoadTime() {
		return Utility.format4(timeLoad*getCharacter().weapon.speedModifier*(1 - prof/2));
	}

	public float getExecutionTime() {
		if (timeExecute >= 0)
			return Utility.format4(timeExecute*getCharacter().weapon.speedModifier*(1 - prof/2));

		Skill skill = (getCharacter().getTarget() != null )? parent.character.getTarget().skillCurrent: null;
		if (skill != null) {
			float time = 0;
			for (Step step : skill.steps)
				if (step.timeExecute >= 0)
				time += step.getLoadTime() + step.getExecutionTime()
						+ step.getCooldownTime();
			return time;
		}
		return new Random().nextInt(5);

	}

	public float getCooldownTime() {
		return Utility.format4(timeCooldown*getCharacter().weapon.speedModifier*(1 - prof/2));
	}

	public Step copy() {
		try {
			return (Step) clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void cancel() {
		//Utility.narrate(parent.character.name + "'s " + name + " is cancelled");
		LogMsg.addLog(parent.character.name + "'s " + name + " is cancelled");
		parent.isExecuting = true;
		Game.getInstance().updateTick(parent.character, 0, Tick.SKILL);
	}

	public Step setMessages(Msg load, Msg execute, Msg cooldown) {
		msgLoad = load;
		msgExecute = execute;
		msgCooldown = cooldown;
		return this;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public float getCP(){
		if (cp == 0)
			return getDamage()/2;
		else
			return cp;
	}
	
	public float getDmgPercent() {
		return dmgPercent*(1 + prof);
	}

	public void setDmgPercent(float dmgPercent) {
		this.dmgPercent = dmgPercent;
	}
	
	public float getHPBuff(){
		return 0;
	}
	public float getMPBuff(){
		return 0;
	}
	public float getSPBuff(){
		return 0;
	}
	public float getBalBuff(){
		return 0;
	}
	public float getHPRegenBuff(){
		return 0;
	}
	public float getMPRegenBuff(){
		return 0;
	}
	public float getSPRegenBuff(){
		return 0;
	}
	public float getBalRegenBuff(){
		return 0;
	}	
	public float getDexBuff(){
		return 0;
	}
	public float getIntBuff(){
		return 0;
	}
	public float getStrBuff(){
		return 0;
	}
	public float getAgiBuff(){
		return 0;
	}
	public float getDefBuff(){
		return 0;
	}
	public float getProtBuff(){
		return 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " [load - " + getLoadTime() + "] [execute - " + getExecutionTime() + "] [Cooldown - " + getCooldownTime() + "] [dmg - " + getDamage()+ "]"; 
	}
}
