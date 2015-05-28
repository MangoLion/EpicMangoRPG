package com.mangolion.epicmangorpg.steps;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Damage;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.components.StatBuff;
import com.mangolion.epicmangorpg.components.Style;
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
import com.mangolion.epicmangorpg.messages.MsgSkillInterrupt;
import com.mangolion.epicmangorpg.messages.MsgSkillInterruptFail;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillParry.StepParry;
import com.mangolion.epicmangorpg.statuses.Buff;
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
	protected float  hpCost = 0, balCost = 0, mpCost = 0, stamCost = 0, percentBlock = 1, subtractDamage = 0;
	protected float  dmgPercent = 0;
	public LinkedList<Element> elements = new LinkedList<Element>();
	public LinkedList<Status> statuses = new LinkedList<Status>();
	public LinkedList<Buff> buffs = new LinkedList<Buff>();
	public boolean isAOE = false, doDamage = false;
	public LinkedList<Character> aoeExceptions = new LinkedList<Character>();
	public boolean useAmmo = false, useItem = false, isCharged = false;
	public int ammoUse = 0, itemUse = 0, maxChage = 1;
	public Item item;	
	
	public Step setUseItem(Item item, int num){
		useItem = true;
		itemUse = num;
		this.item = item;
		return this;
	}
	
	public boolean checkConndition(String arg){
		return checkConndition();
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
		if (getCharacter().getSp() <getStamCost()*(prof + 1) || getCharacter().getMp() < getMpCost()*(prof + 1) || getCharacter().getBal() < getBalCost()*(prof + 1) || getCharacter().getHp() < getHpCost()*(prof + 1) || (getCharacter().weapon.useAmmo && getCharacter().weapon.ammo < ammoUse)){
			Utility.narrate("You do not have enough sp/mp to use " + name);
			return false;
		}
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
		this.elements.add(element);
		return this;
	}
	
	public Step setStatus(Status status, float chance){
		this.statuses.add(status);
		chanceStatus = chance;
		return this;
	}
	
	public float getDamage(){
		float skilDmg = 0;
		for (Skill skill: getCharacter().skills)
			if (skill.type == ActionType.WeaponMastery && skill.checkWeapon(getCharacter().weapon))
				skilDmg = skill.getTotalDamagePercent()+1;
		float dmgbaseTemp = this.dmgBase;
		dmgbaseTemp -= subtractDamage;
		subtractDamage = 0;
		if (strBased){
		if (getCharacter().weapon.checkType(Weapons.LauncherGrenade) && parent.weapons.contains(Weapons.LauncherGrenade))
			return (getDmgPercent()*(getCharacter().weapon.launcherDamage + dmgbaseTemp)*getCharacter().weapon.gunMod)*skilDmg;
		if (getCharacter().weapon.checkType(Weapons.Bow))
			return (getDmgPercent()*(getCharacter().weapon.bowDamage + dmgbaseTemp)*getCharacter().weapon.gunMod)*skilDmg;
		if (getCharacter().weapon.checkType(Weapons.Gun))
			return (getDmgPercent()*(getCharacter().weapon.gunDamage + dmgbaseTemp)*getCharacter().weapon.gunMod)*skilDmg;
		if (getCharacter().weapon.checkType(Weapons.Cylinder))
			return (getDmgPercent()*(getCharacter().weapon.alchemyDamage + dmgbaseTemp)*getCharacter().weapon.alchemyMod)*skilDmg;
		else
			return ((getCharacter().weapon.baseDamage + dmgbaseTemp + getCharacter().getStrDamage())*getDmgPercent()*getCharacter().weapon.meleeDamageModifier)*skilDmg;
		}
		else if (intBased)
			return ((getCharacter().weapon.magicDamage + dmgbaseTemp + getCharacter().getIntDamage())*getDmgPercent()*getCharacter().weapon.baseMagicDmgMod)*skilDmg;
		else
			return (dmgbaseTemp*(1+prof))*getDmgPercent()*skilDmg;
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
	
	private LinkedList<Status> getStatuses() {
		LinkedList<Status> result = new LinkedList<Status>();
		for (Status status: statuses){
		Status newStatus =status.copy();
		newStatus.character = getCharacter().getTarget();
		newStatus.time *= (prof + 1);
		result.add(newStatus);
		}
		return result;
	}
	
	public boolean damage(Character target){
		return damage(target, true);
	}
	public boolean damage(Character target, boolean checkMiss){
		if ((type == ActionType.MeleeBlock || type == ActionType.MeleeSpecial||type == ActionType.MeleeStab||type == ActionType.MeleeSwing) && target.isAirborne()){
			getCharacter().changeStyle(-0.5f);
			StylePainter.append(new Msg("$name cannot reach $targetname because $p is airborne!").getMessage(getCharacter(), target, 0), Style.getSegments(-0.5f, getCharacter()));
			return false;
		}

		if (!isAOE){
			return damageSingle(target, checkMiss);
		}else
			for (Character character: Game.getInstance().getEnemies(getCharacter())){
				boolean excepted = false;

				for (Character character2 : aoeExceptions){
				if (character == character2)
					excepted = true;
				}
				if (excepted)
					continue;
				
				damageSingle(character, checkMiss);
			}
		aoeExceptions.clear();
			addProf(new Proficiency(getCharacter(), target));
			subtractDamage = 0;
			return true;
	}
	
	public boolean damageSingle(Character target, boolean checkMiss){
		float dmg;
		//used for showing the miss message, style is calculated based on the damage of the missed skill, thats why the msg needs to be shown only after the damage is calculated
		boolean hasMissed = false;
		float miss = ( chanceMiss) + (1 - getCharacter().getAccuracy(target));
		if (getCharacter().weapon.isAutomatic)
			miss *= 1.2f;
		System.out.println("miss: " + miss);
		if (rand.nextFloat() <= miss && checkMiss) {
			hasMissed = true;
			if (target.skillCurrent != null && target.skillCurrent.type == ActionType.Dodge){
				target.skillCurrent.steps.get(target.skillCurrent.stepCurrent).addProf(new Proficiency(target, getCharacter()));
			}
		}	

	//need to call damage here to determine style points
	dmg= getDamage();
	
	if (hasMissed){
		float change = Style.positive(getCharacter(), target ,Style.miss, 1 - miss, dmg);
		StylePainter.append(new Msg("$name's " + name + " slice the air in a wide arc, missing its target.").getMessage(false, getCharacter(),
				target, 0), Style.getSegments(change, getCharacter()));
		return false;
	}
	
	if (calculateChanceMelee(target))
		return false;
	
	//get damage thats subtracted
	dmg= getDamage();
	float crit = getCharacter().getCritical(target) + critBase;
	System.out.println("crit: " + crit);
	if (rand.nextFloat() <= crit) {
		StylePainter.append(new Msg("$name has landed a critical!").getMessage(getCharacter(), null, 0));
		dmg *= 1.5;
	}
	
		int type = getType();
		
		Damage damage = new Damage(getCharacter(), dmg, type).setStatuses(statuses).setBuffs(buffs);
		damage.elements.addAll(getDmgElements());
		if (type == Damage.MELEE)
				damage.barrierNegation = getCharacter().getBarrierNegate();
		target.setDamage(damage);
		return true;
	}

	public int getType(){
		int type;
		if (intBased)
			type = Damage.MAGIC;
		else if (doDamage)
			type = Damage.MELEE;
		else
			type = Damage.RANGE;
		return type;
	}
	
	public LinkedList<Element> getDmgElements(){
		LinkedList<Element>results = new LinkedList<Element>();
		if (elements.size() > 0)
			results.addAll(elements);
		else
			results.addAll(getCharacter().getElements());
		return results;
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
		, msgParry = new MsgParrySuccess(), msgSuccess/* = new Msg("$name has successfully executed $skill")*/;
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
	public void load() {
		
		if (useItem&& (!isCharged || (isCharged && getCharacter().skillCharged == null)))
			getCharacter().inventory.removeItem(item, itemUse);
		
		if (useAmmo && rand.nextFloat() <= getCharacter().weapon.chanceJam){
			getCharacter().weapon.isJammed = true;
			StylePainter.append(new Msg("$name's $weapon is jammed!").getMessage(getCharacter(), null, 0));
			cancel();
			return;
		}
	
		LogMsg.addLog(new LogMsg(getCharacter().name + " is loading " + name
				 + ": " + getLoadTime() + " sec", Game.getInstance().timePassed));
		if (getLoadTime() > 0)
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
		else
			Utility.narrate("-");
		getCharacter().useStamina(getStamCost()*(prof + 1)/2);
		getCharacter().useMana(getMpCost()*(prof + 1)/2);
	}

	public void execute() {
		execute(getCharacter().getTarget());
	}
	
	public void execute(Character target){
		execute(target, getExecutionTime());
	}
	
	public void execute(Character target, float time) {
		execute(target, time, "");
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
		
		if (isCharged&& getCharacter().skillCharged != parent){
			getCharacter().skillCharged = parent;
			return;
	}
		
		getCharacter().useStamina(getStamCost()*(prof + 1)/2);
		getCharacter().useMana(getMpCost()*(prof + 1)/2);
		if (useAmmo){
		getCharacter().weapon.ammo -= ammoUse;
		Weapon weapon = getCharacter().weapon;
		Utility.narrate(getCharacter().name + "'s "  + weapon.name + " has " + weapon.ammo + " ammo left.");
		}

		LogMsg.addLog(new LogMsg(getCharacter().name + " is executing " + name
				 + ": " +time + " sec", Game.getInstance().timePassed));
		if (getExecutionTime() > 0)
		if (msgExecute == null)
			StylePainter.append(new Msg("$name is executing $skill, duration: " + time + " seconds").getMessage(getCharacter(), target, 0));
		else
			StylePainter.append(msgExecute.getMessage(parent.character,
					target, getExecutionTime()));
		else
			Utility.narrate("-");
		
		if ((doDamage && damage(target)) || !doDamage){
			if (msgSuccess != null)
				StylePainter.append(msgSuccess.getMessage(parent.character,
						target, getExecutionTime()));
		}
	}

	public void cooldown() {
		if ((type == ActionType.MeleeBlock || type == ActionType.MeleeSpecial||type == ActionType.MeleeStab||type == ActionType.MeleeSwing) && getCharacter().isAirborne()){
			StylePainter.append(new Msg("$name is no longer airborne.").getMessage(getCharacter(), null, 0));
			getCharacter().removeBuff("Airborne");
			return;
		}

		LogMsg.addLog(new LogMsg(getCharacter().name + "'s  " + name
				+ " is on cooldown: "
				+ getCooldownTime() + " sec", Game.getInstance().timePassed));
		if (getCooldownTime() > 0)
		if (msgCooldown == null)
			StylePainter.append(new Msg("$name's $skill has completed and has left an opening for " + getCooldownTime() + " seconds").getMessage(getCharacter(), null, 0));
		else
			StylePainter.append(msgCooldown.getMessage(parent.character,
					parent.character.getTarget(), getCooldownTime()));
		else
			Utility.narrate("-");
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
		return setMessages(load, execute, cooldown, null);
	}

	public Step setMessages(Msg load, Msg execute, Msg cooldown, Msg success) {
		msgLoad = load;
		msgExecute = execute;
		msgCooldown = cooldown;
		msgSuccess = success;
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
	
	@Override
	public float getMagicDefBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	public float getProtBuff(){
		return 0;
	}
	
	@Override
	public float getBarrierNegate() {
		// TODO Auto-generated method stub
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
		return hpCost*(prof + 1)*getCharacter().getHpCost();
	}

	public void setHpCost(float hpCost) {
		this.hpCost = hpCost;
	}

	public float getBalCost() {
		if (isCharged && getCharacter().skillCharged == parent)
			return 0;
		return balCost*(prof + 1)*getCharacter().getBalCost();
	}

	public void setBalCost(float balCost) {
		this.balCost = balCost;
	}

	public float getMpCost() {
		if (isCharged && getCharacter().skillCharged == parent)
			return 0;
		return mpCost*(prof + 1)*getCharacter().getMpCost();
	}

	public void setMpCost(float mpCost) {		
		this.mpCost = mpCost;
	}

	public float getStamCost() {
		if (isCharged && getCharacter().skillCharged == parent)
			return 0;
		return stamCost*(prof + 1)*getCharacter().getSpCost();
	}

	public void setStamCost(float stamCost) {
		this.stamCost = stamCost;
	}
	
	@Override
	public float getHPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getMPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getSPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getBalCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public float getMeleeDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getGunDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getCylinderDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getBowDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getMagicDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getMeleeSpeedBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getMagicSpeedBuff() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " [load - " + getLoadTime() + "] [execute - " + getExecutionTime() + "] [Cooldown - " + getCooldownTime() + "] [dmg - " + getDamage()+ "]"; 
	}
	
	public boolean calculateChanceMelee(Character target) {
		if (target == null)
			return false;
		


		Skill skill = target.skillCurrent;
		Step step = target.getCurrentStep();
		if (step == null)
			return false;
		if (skill.isLoading) {
			int balCal = (int) (100 - target.getBal() / target.getMaxBal() / 2*100)+ 1;
			System.out.println("bal: " + balCal);
			if (balCal > 0 && rand.nextInt(balCal) == 0) {
				float change = Style.positive(getCharacter(), target, Style.interrupt, (100f-balCal)/100f);
				StylePainter.append(new MsgSkillInterrupt().getMessage(
						false, getCharacter(), target, 0), Style.getSegments(change, getCharacter()));
				step.cancel();
			} else {
				StylePainter.append(new MsgSkillInterruptFail().getMessage(
						getCharacter(), target, 0));
			}
		}

		/*if (!isAOE && step.type == ActionType.Dodge && skill.isExecuting
				&& rand.nextFloat() <= (step.chanceDodge) /  getCharacter().weapon.sizeModifier) {
			StylePainter.append(msgMiss.getMessage(getCharacter(), target, 0));
			step.addProf(new Proficiency(target, getCharacter()));
			aoeExceptions.add(target);
			cancel();
			return true;
		}*/
		if (step.type == ActionType.MeleeParry
				&& skill.isExecuting
				&& rand.nextFloat() <= ((StepParry) step).chanceParry*chanceParry
						/ getCharacter().weapon.sizeModifier) {
			step.addProf(new Proficiency(target, getCharacter()));
			float change = Style.positive(target, getCharacter(), Style.parry,1-((StepParry) step).chanceParry*chanceParry
					/ getCharacter().weapon.sizeModifier);
			StylePainter.append(msgParry.getMessage(false, target, getCharacter(), 0), Style.getSegments(change, target));
			aoeExceptions.add(target);
			cancel();
			return true;
		}
		if (step.type == ActionType.MeleeBlock && skill.isExecuting && chanceBlock > 0) {
			StylePainter.append(new Msg("$name's $skill is blocked by $targetname's $targetskill").getMessage(getCharacter(), target, 0));
			subtractDamage = step.value;
			System.out.println(target.name+ " defense value: " + subtractDamage);
			//aoeExceptions.add(target);
			step.addProf(new Proficiency(target, getCharacter()));
			return false;
		}
		if ((step.type == ActionType.MeleeSwing || step.type == ActionType.MeleeStab)
				&& skill.isExecuting) {
			if (rand.nextFloat() <= chanceBlock /  getCharacter().weapon.sizeModifier) {
				StylePainter.append(new Msg("$name's $skill is blocked by $targetname's $targetskill").getMessage(getCharacter(), target, 0));
				subtractDamage = Math.abs(step.getDamage()/2); 
				return false;
			} else if ((step.parent.isLoading || step.parent.isCooldown)
					&& rand.nextFloat() <= chanceParry /  getCharacter().weapon.sizeModifier) {
				StylePainter.append(new Msg("$name's $skill is parried by $targetname's $targetskill").getMessage(getCharacter(), target, 0));
				step.addProf(new Proficiency(target, getCharacter()));
				//aoeExceptions.add(target);
				step.cancel();
				cancel();
				return true;
			}
		}

		return false;
	}

	public Step setBuff(Buff ... buffs){
		this.buffs.addAll(Arrays.asList(buffs));
		return this;
	}

}
