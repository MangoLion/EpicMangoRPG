package com.mangolion.epicmangorpg.steps;

import java.util.LinkedList;
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
import com.mangolion.epicmangorpg.items.Inventory;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgParrySuccess;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public abstract class Step implements Cloneable, StatBuff {
	public ActionType type;
	public Skill parent;
	public String name, desc;
	public Random rand = new Random();
	public float chanceMiss = 0, chanceDodge  = 1, chanceParry = 1, chanceBlock =1,
			prof = 0, timeLoad, timeExecute, timeCooldown, value = 0, chanceStatus = 0, cp = 0, dmgBase = 0, critBase = 0, timeChargeLoad = 0.1f, timeChargeExecute = 0.1f, timeChargeCD = 0.1f;
	protected float  hpCost = 0, balCost = 0, mpCost = 0, stamCost = 0, percentBlock = 1;
	protected float  dmgPercent = 0;
	public Element element;
	public Status status;
	public boolean isAOE = false;
	public LinkedList<Character> aoeExceptions = new LinkedList<Character>();
	public boolean useAmmo = false, useItem = false, isCharged = false;
	public int ammoUse = 0, itemUse = 0;
	public Item item;
	
	public Step setUseItem(Item item, int num){
		useItem = true;
		itemUse = num;
		this.item = item;
		return this;
	}
	 
	public boolean checkConndition(){
		if (isCharged)
			if (getCharacter().skillCharged != null && getCharacter().skillCharged != parent){
				StylePainter.append(new Msg("$name cannot do " + name + " because $p already is charged with " + getCharacter().skillCharged.name).getMessage(getCharacter(), null, 0));
				return false;
			}
			
		if (useItem && (!isCharged || (isCharged && getCharacter().skillCharged == null))){
			Inventory inv = getCharacter().inventory;
			if (inv.getItemNumber(item) < itemUse){
				Utility.narrate(name + " needs " + itemUse + " " + item.name  + ", " + getCharacter().name + " only has " + inv.getItemNumber(item)) ;
				return false;
			}
		}
		
		if (useAmmo){
			Weapon weapon = getCharacter().weapon;
			if (weapon.isAutomatic)
				ammoUse = getCharacter().weapon.fireRate;
			else 
				ammoUse = 1;
			if (getCharacter().weapon.ammo < ammoUse){
				Utility.narrate(getCharacter().name + " doesn't have enough ammo, weapon has " + getCharacter().weapon.ammo + " ammo while " + ammoUse + " is required.");
				return false;				
			}else if(getCharacter().weapon.isJammed){
				Utility.narrate(getCharacter().name + " can't do that because his weapon is jammed.");
				return false;				
			}
		}
		if (getCharacter().getSp() <stamCost*(prof + 1) || getCharacter().getMp() < mpCost*(prof + 1) || getCharacter().getBal() < balCost*(prof + 1) || getCharacter().getHp() < hpCost*(prof + 1) || (getCharacter().weapon.useAmmo && getCharacter().weapon.ammo < ammoUse))
			return false;
		return true;
	}
	
	public boolean cancelfromStun = true, strBased = true, intBased = false;;
	
	public void init(){
		
	}
	
	public Step setCost(float sp,float mp,float bal,float hp){
		stamCost = sp;
		mpCost = mp;
		balCost = bal;
		hpCost = hp;
		return this;
	}
	
	public Step setAOE(boolean s){
		isAOE = s;
		return this;
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
		if (getCharacter().weapon.checkType(Weapons.Gun))
			return (getDmgPercent()*(getCharacter().weapon.gunDamage + dmgBase)*getCharacter().weapon.gunMod)*skilDmg;
		if (getCharacter().weapon.checkType(Weapons.Cylinder))
			return (getDmgPercent()*(getCharacter().weapon.alchemyDamage + dmgBase)*getCharacter().weapon.alchemyMod)*skilDmg;
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
				Game.getInstance().floorPercent+= p.type*10;
			else if(Game.getInstance().lastFloor)
				Game.getInstance().floorPercent -= p.type*20;
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
		float miss = ( chanceMiss) + (1 - getCharacter().getAccuracy(target));
		if (getCharacter().weapon.isAutomatic)
			miss *= 1.3f;
		System.out.println("" + miss);
		if (rand.nextFloat() <= miss) {
			StylePainter.append(new MsgSlashMiss().getMessage(getCharacter(),
					target, 0));
			return;
		}
		
		float dmg = getDamage() - subtract;
		
		if (dmg <= 0)
			dmg = 1;
		
		float crit = getCharacter().getCritical(target) + critBase;
		if (rand.nextFloat() <= crit) {
			StylePainter.append(new Msg("$name has landed a critical!").getMessage(getCharacter(), null, 0));
			dmg *= 1.5;
		}
		
		if (!isAOE){
			target.setDamage(getCharacter(), dmg);
		if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
			target.addStatus(getStatus());
		}else
			for (Character character: Game.getInstance().getEnemies(getCharacter())){
				character.setDamage(getCharacter(), dmg);
				if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
					character.addStatus(getStatus());
			}
		
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
	
		
		float miss = ( chanceMiss) + (1 - getCharacter().getAccuracy(target));
		if (getCharacter().weapon.isAutomatic)
			miss *= 1.3f;
		System.out.println("" + miss);
		if (rand.nextFloat() <= miss) {
			StylePainter.append(new MsgSlashMiss().getMessage(getCharacter(),
					target, 0));
			return;
		}
		
		float eleMult = -2;
		for (Element element: target.getElements()){
			if (this.element != null)
				eleMult = (eleMult == -2)? Elements.getElement(element.type).calculate(this.element.type): (eleMult +  Elements.getElement(element.type).calculate(this.element.type))/2;
				else if (getCharacter().getElements().size() > 0)
					eleMult = (eleMult == -2)?  Elements.getElement(element.type).calculate(getCharacter().getElements().getFirst().type): (eleMult +  Elements.getElement(element.type).calculate(getCharacter().getElements().getFirst().type))/2;
		}
		//System.out.println(getCharacter().name + " " + eleMult);
		if (eleMult == -2)
			eleMult = 1;
		float dmg = getDamage()*eleMult;
		
		float crit = getCharacter().getCritical(target) + critBase;
		System.out.println("" + crit);
		if (rand.nextFloat() <= crit) {
			StylePainter.append(new Msg("$name has landed a critical!").getMessage(getCharacter(), null, 0));
			dmg *= 1.5;
		}
		
		if (!isAOE){
			target.setDamage(getCharacter(), dmg);
		if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
			target.addStatus(getStatus());
		}else
			for (Character character: Game.getInstance().getEnemies(getCharacter())){
				boolean excepted = false;
				for (Character character2 : aoeExceptions){
					System.out.println(character.name + " " + character2.name); 
				if (character == character2)
					excepted = true;
				}
				if (excepted)
					continue;
				
				character.setDamage(getCharacter(), dmg);
				if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
					character.addStatus(getStatus());
			}
		aoeExceptions.clear();
			addProf(new Proficiency(getCharacter(), target));
	}
	
	public Step setChances(float block, float parry, float miss) {
		chanceBlock = block;
		chanceParry = parry;
		chanceMiss = miss;
		return this;
	}
	
	public void releaseCharge(Character target, float time){
		
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

	public Step(Skill parent, String name,  String desc, ActionType type, float timeLoad, float timeExecute,
			float timeCooldown, float dmgPercent) {
		this.parent = parent;
		this.name = name;
		this.type = type;
		this.timeLoad = timeLoad;
		this.timeExecute = timeExecute;
		this.timeCooldown = timeCooldown;
		this.desc = desc;
		this.dmgPercent = dmgPercent;
		init();
	}

/*	public Step(Skill parent, String name, String desc,  ActionType type, float timeLoad,
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
	}*/

	public void load() {
		
		if (useItem&& (!isCharged || (isCharged && getCharacter().skillCharged == null)))
			getCharacter().inventory.removeItem(item, itemUse);
		
		if (useAmmo && rand.nextFloat() <= getCharacter().weapon.chanceJam){
			getCharacter().weapon.isJammed = true;
			StylePainter.append(new Msg("$name's $weapon is jammed!").getMessage(getCharacter(), null, 0));
			cancel();
			return;
		}
		
		
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
		getCharacter().useStamina(stamCost*(prof + 1)/2);
		getCharacter().useMana(mpCost*(prof + 1)/2);
	}

	public void execute() {
		execute(getCharacter().getTarget());
	}
	
	public void execute(Character target){
		execute(target, getExecutionTime());
	}
	
	public void execute(Character target, float time) {
		execute(target, getExecutionTime(), "");
	}
	
	public void execute(Character target, String aug) {
		execute(target, getExecutionTime(), aug);
	}
	
	public void execute(Character target, float time, String aug){
		if (isCharged){
			if (getCharacter().skillCharged == parent){
				getCharacter().skillCharged = null;
				releaseCharge(target, time);
			}
			else
				getCharacter().skillCharged = parent;
		}
		
		if ((type == ActionType.MeleeBlock || type == ActionType.MeleeSpecial||type == ActionType.MeleeStab||type == ActionType.MeleeSwing) && target.isAirborne()){
			StylePainter.append(new Msg("$name cannot reach $targetname because $p is airborne!").getMessage(getCharacter(), target, 0));
			return;
		}
		getCharacter().useStamina(stamCost*(prof + 1)/2);
		getCharacter().useMana(mpCost*(prof + 1)/2);
		if (useAmmo){
		getCharacter().weapon.ammo -= ammoUse;
		Weapon weapon = getCharacter().weapon;
		Utility.narrate(getCharacter().name + "'s "  + weapon.name + " has " + weapon.ammo + " ammo left.");
		}
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
		if ((type == ActionType.MeleeBlock || type == ActionType.MeleeSpecial||type == ActionType.MeleeStab||type == ActionType.MeleeSwing) && getCharacter().isAirborne()){
			StylePainter.append(new Msg("$name is no longer airborne.").getMessage(getCharacter(), null, 0));
			getCharacter().removeBuff("Airborne");
			return;
		}
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
		if (isCharged && getCharacter().skillCharged == parent)
			return timeChargeLoad;
		return Utility.format4(timeLoad*getCharacter().getMeleeSpeed()*(1 - prof/2));
	}

	public float getExecutionTime() {
		if (isCharged && getCharacter().skillCharged == parent)
			return timeChargeExecute;
		if (timeExecute > 0)
			return Utility.format4(timeExecute*getCharacter().getMeleeSpeed()*(1 - prof/2));

		Skill skill = (getCharacter().getTarget() != null && getCharacter().getTarget() != getCharacter() )? parent.character.getTarget().skillCurrent: null;
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
		if (isCharged && getCharacter().skillCharged == parent)
			return timeChargeCD;
		return Utility.format4(timeCooldown*getCharacter().getMeleeSpeed()*(1 - prof/2));
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
			return getDmgPercent()*10;
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
	public float getAccuracyBuff() {
		// TODO Auto-generated method stub
		return 0;
	};
	
	@Override
	public float getCriticalBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public float getHpCost() {
		if (isCharged && getCharacter().skillCharged == parent)
			return 0;
		return hpCost*(prof + 1);
	}

	public void setHpCost(float hpCost) {
		this.hpCost = hpCost;
	}

	public float getBalCost() {
		if (isCharged && getCharacter().skillCharged == parent)
			return 0;
		return balCost*(prof + 1);
	}

	public void setBalCost(float balCost) {
		this.balCost = balCost;
	}

	public float getMpCost() {
		if (isCharged && getCharacter().skillCharged == parent)
			return 0;
		return mpCost*(prof + 1);
	}

	public void setMpCost(float mpCost) {		
		this.mpCost = mpCost;
	}

	public float getStamCost() {
		if (isCharged && getCharacter().skillCharged == parent)
			return 0;
		return stamCost*(prof + 1);
	}

	public void setStamCost(float stamCost) {
		this.stamCost = stamCost;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " [load - " + getLoadTime() + "] [execute - " + getExecutionTime() + "] [Cooldown - " + getCooldownTime() + "] [dmg - " + getDamage()+ "]"; 
	}
}
