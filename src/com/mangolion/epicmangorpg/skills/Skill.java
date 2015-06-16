package com.mangolion.epicmangorpg.skills;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.StatBuff;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgFinishCD;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class Skill implements StatBuff {

	protected Random rand = new Random();
	public Character character;
	public String name, desc;
	public LinkedList<Step> steps = new LinkedList<Step>();
	public boolean isExecuting, isCooldown, isLoading, isObservable = false, isTwoHanded = true, hasTarget = true, customTime = false, hasArg = false, requireAllType = false;
	public int stepCurrent;
	public LinkedList<Weapons> weapons = new LinkedList<Weapons>();
	public ActionType type;
	public float chanceObserve = 0, prof = 0, dmgBoost = 1, shopPrice = 0, tick;
	public String aug = "";
	LinkedList<String>arguments = new LinkedList<String>();
	
	public LinkedList<String> getArguments(){
		return arguments;
	}
	public void setArguments(String ... args){
		hasArg = true;
		this.arguments.addAll(Arrays.asList(args));
	}
	
	public void setObservable(boolean ob, float chance){
		isObservable = ob;
		chanceObserve = chance;
	}

	public Skill(String name, String desc, ActionType type, Weapons ... weapon_) {
		this.name = name;
		this.desc = desc;
		weapons.addAll(Arrays.asList(weapon_));
		this.type = type;
	}
	
	public Skill(String name,  String desc, Weapons weapon_,  ActionType type) {
		this.name = name;
		this.desc = desc;
		weapons.add(weapon_);
		this.type = type;
	}
	
	public boolean checkCompatability (Character target){
		Step step = getCurrentStep();
		if (!step.checkCompatability(target))
			return false;
		return true;
	}
	
	public int getType(){
		return steps.getFirst().getType();
	}
	
	/*
	 * Only available for passive skills
	 */
	public void tick(float dTime){
		
	}

	public void addSteps(Step... steps) {
		if (steps != null) {
			this.steps.addAll(Arrays.asList(steps));
		}
	}

	public void cancel() {
		LogMsg.addLog(character.name + "'s " + name + " is cancelled");
		reset();
		tick = 0;
	}

	public void complete() {
		reset();
	}
	Msg msgCooldown = new MsgFinishCD();
	public boolean  execute() {
		return execute(character.getTarget());
	}
	
	public boolean execute(Character target){
		return execute(target, -1);
	}
	
	public boolean execute(Character target, float custime){
		return execute(target, custime, "");
	}
	public boolean execute(Character target, String aug) {
		return execute(target, -1, aug);
	}
	
	public boolean execute(Character target, float custime,  String aug) {
		if (!aug.equals(""))
			this.aug = aug;
		character.skillCurrent = this;
		character.setTarget(target);
		Step step = null;
		float time = 0;

		if (isCooldown) {
			isCooldown = false;
			if (stepCurrent >= steps.size() - 1) {
				//Utility.narrate(character.name
				//		+ "'s cooldown time is over, and  is  now ready to prepare another skill");
				LogMsg.addLog(new LogMsg(character.name	+ "'s cooldown time is over, and  is  now ready to prepare another skill", Game.getInstance().timePassed));
				if (character == CharacterPlayer.instance)
					StylePainter.append(msgCooldown.getMessage(character, null, 0));
				complete();
				return true;
			} else {
				stepCurrent++;
				resetStep();
				//Utility.narrate(character.name + "'s cooldown time is over");
				LogMsg.addLog(new LogMsg(character.name	+ "s cooldown time is over", Game.getInstance().timePassed));
				StylePainter.append(msgCooldown.getMessage(character, null, 0));
			}

		}

		if (stepCurrent < steps.size())
			step = steps.get(stepCurrent);

		if (isExecuting) {
			isExecuting = false;
			isCooldown = true;
			time = step.getCooldownTime();
			tick = time;

			step.cooldown();
		} else if (isLoading) {
			isLoading = false;
			isExecuting = true;
			Game.getInstance().checkforObservation(character);
			time = step.getExecutionTime();
			if (step.isCustomTime() && step.customExecutionTime != -1){
				tick =  step.customExecutionTime;
				step.execute(target, step.customExecutionTime);
				step.customExecutionTime = -1;
			}
			else{
				tick = time;
				if (this.aug.equals(""))
					step.execute(target);
				else
					step.execute(target, this.aug);
				this.aug = "";
			}
		} else {
			if (!checkWeapon(character.weapon)){
				boolean changed = false;
				for (ItemCustom item: character.inventory.itemCustoms)
					if (item instanceof Weapon && checkWeapon((Weapon) item)){
						character.equip(item);
						changed = true;
						break;
					}
				if (!changed){
					Utility.narrate(character.name + " do not have the right weapon to use this skill");
					return false;
				}
			}
			
			boolean check;
			if (!hasArg)
				check = step.checkConndition();
			else
				check = step.checkConndition(this.aug);
			if (!check){
				if (character == CharacterPlayer.instance)
				return false;
			}
			
			character.skillsRecent.add(this);
			isLoading = true;
			time = step.getLoadTime();
			tick = time;
			if (custime != -1 && step.isCustomTime())
				step.customExecutionTime = custime;
			step.load();
		}
		return true;
	}

	public void reset() {
		stepCurrent = 0;
		isExecuting = false;
		isCooldown = false;
		isLoading = false;
		character.skillCurrent = null;
		//if (!character.isStunned())
	//		character.nextAction();
		for (Step step: steps)
			step.reset();
	}

	public void resetStep() {
		isExecuting = false;
		isCooldown = false;
		isLoading = false;
	}

	public Skill copy() {
		Skill result = new Skill(name, desc, null, type);
		for (Step step : steps)
			result.steps.add(step.copy());
		return result;
	}

	public float getTotalLoadTime() {
		float result = 0;
		for (Step step: steps)
			result += step.getLoadTime();
		return Utility.format(result);
	}

	public float getTotalExecutionTime() {
		float result = 0;
		for (Step step: steps)
			result += step.getExecutionTime();
		return Utility.format(result);
	}

	public float getTotalCooldownTime() {
		float result = 0;
		for (Step step: steps)
			result += step.getCooldownTime();
		return Utility.format(result);
	}
	
	public boolean checkCondition(){
		Step step = steps.get(stepCurrent);
		return step.checkConndition();
	}
	
	public float getTotalTime(){
		return Utility.format(getTotalLoadTime() + getTotalExecutionTime() + getTotalCooldownTime());
	}

	public float getTotalHPCost() {
		float result = 0;
		for (Step step: steps)
			result += step.getHpCost();
		return Utility.format(result);
	}

	public float getTotalMPCost() {
		float result = 0;
		for (Step step: steps)
			result += step.getMpCost();
		return Utility.format(result);
	}
	
	public float getTotalSPCost() {
		float result = 0;
		for (Step step: steps)
			result += step.getStamCost();
		return Utility.format(result);
	}
	
	public float getTotalBalCost() {
		float result = 0;
		for (Step step: steps)
			result += step.getBalCost();
		return Utility.format(result);
	}

	public float getTotalDamage() {
		float result = 0;
		for (Step step: steps)
			result += step.getDamage();
		return Utility.format(result);
	}
	
	public float getTotalDamagePercent() {
		float result = 0;
		for (Step step: steps)
			result += step.getDmgPercent();
		return Utility.format(result);
	}
	
	public float getCP(){
		float result = 0;
		//for (Step step : steps)
		//	result += step.getCP();
		return result;
	}
	
	public float getHPBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getHPBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getMPBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getMPBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getSPBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getSPBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getBalBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getBalBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getHPRegenBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getHPRegenBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getMPRegenBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getMPRegenBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getSPRegenBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getSPRegenBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getBalRegenBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getBalRegenBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getDexBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getDexBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getIntBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getIntBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getStrBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getStrBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getAgiBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getAgiBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getDefBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getDefBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	public float getProtBuff(){
		float result = 0;
		for (Step step: steps)
			result += step.getProtBuff();
		return (result > 0)? Utility.format4(result): 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	public float getProf() {
		float prof = 0;
		if (type.getGeneralType() != GeneralType.Passive)
		for (Step step: steps){
			prof += step.prof;
			return prof/steps.size();
		}
		return this.prof;
	}

	public boolean checkWeapon(Weapon weapon) {
		
		for (Weapons w: weapons)
			for (Weapons w2:weapon.type)
				if (w == w2 || w == Weapons.ALL)
					return true;
		return false;
	}
	
	public boolean checkWeapon(Weapons weapon) {
		
		for (Weapons w: weapons)
				if (w == weapon || w == Weapons.ALL)
					return true;
		return false;
	}

	@Override
	public float getAccuracyBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCriticalBuff() {
		// TODO Auto-generated method stub
		return 0;
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
	public float getMagicDefBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getBarrierNegate() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Step getCurrentStep() {
		// TODO Auto-generated method stub
		return steps.get(stepCurrent);
	}


}
