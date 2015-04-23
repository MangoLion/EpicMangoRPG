package com.mangolion.epicmangorpg.characters;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import javax.rmi.CORBA.Util;

import com.mangolion.epicmangorpg.ais.AI;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Inventory;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class Character implements Cloneable {
	public Character target;
	Random rand = new Random();
	public String name, desc, pronoun = "he", pronoun2 = "him", pronoun3 =  "his", pronoun4 = "himself";
	public Skill skillCurrent;
	public LinkedList<Skill> skills = new LinkedList<Skill>();
	public LinkedList<Status> statuses = new LinkedList<Status>();
	public LinkedList<Buff> buffs = new LinkedList<Buff>();
	public Weapon weapon;
	public boolean isPlayer = false,
								isAllied = false,
								isDead = false,
								isSupporter = false,
								isBoss = false;
	public AI ai;
	public Inventory inventory = new Inventory();

	public float hp, mp, sp, str, agi, bal, inte, dex, maxHP, maxMP, maxSP, maxBal, prot, def, meleeSpeedMod = 1, magicSpeedMod = 1, hpRegen = 0, mpRegen =0.01f, spRegen = 0.02f, balRegen = 0.05f, cpBase = 0 , learnRate = 0;;

	public Character(String name, String desc, float hp, float mp, float stam, float str,
			float agi, float bal, float inte, float dex, float def, float prot, Weapon weapons, Skill... skills) {
		this.name = name;
		maxHP = hp;
		maxMP = mp;
		maxSP = stam;
		maxBal = bal;
		this.hp = maxHP;
		this.mp = maxMP;
		this.sp  = maxSP;
		this.bal = maxBal;
		this.str = str;
		this.agi = agi;
		this.bal = bal;
		this.inte = inte;
		this.dex = dex;
		this.def = def;
		this.prot = prot;
		this.weapon = weapons;
		this.desc = desc;
		addSkills(skills);
	}

	public void addSkills(Skill... skills) {
		if (skills != null) {
			this.skills.addAll(Arrays.asList(skills));
			for (Skill skill : skills)
				skill.character = this;
		}
	}
	
	public void addStatus(Status stat){
		StylePainter.append(new Msg( name + " is " + stat.name +" for $num seconds").getMessage(null, null, stat.time));
		for (Status status: statuses)
			if (status.name.equals(stat.name)){
				status.multiplier = stat.multiplier;
				status.time += stat.time;
				return;
			}
		statuses.add(stat);
	}

	public void nextAction() {
		if (target == null || (target != null && target.isDead))
			if (!isSupporter)
				target = Game.getInstance().findEnemy(this);
			else
				target = Game.getInstance().findAlly(this);
		if (ai != null)
			ai.nextAction();
	}

	public void tick(float deltaTime) {
		for (Status status : statuses){
			status.tick(deltaTime);
		}
		if (skillCurrent == null && Game.getInstance().findTick(this) == null && !isStunned())
			nextAction();
		else if(skillCurrent != null && Game.game.findTick(this) == null)
			skillCurrent.execute();
		regen(deltaTime);
	}
	
	public void regen(float deltaTime){
		if (bal < getMaxBal())
			bal += Math.round(deltaTime*getMaxBal()*balRegen*100f)/100f;
		if (sp < getMaxSP())
			sp += Math.round(deltaTime*getMaxSP()*spRegen*100f)/100f;
		if (hp < getMaxHP())
			hp += Math.round(deltaTime*getMaxHP()*hpRegen*100f)/100f;
		if (mp < getMaxMP())
			mp += Math.round(deltaTime*getMaxMP()*mpRegen*100f)/100f;
	}
	
	public void applyBuff(Buff buff){
		for (Buff b: buffs)
			if (b.name.equals(buff.name) && b.value == buff.value){
				b.time += buff.time;
				return;
			}
		buffs.add(buff);		
	}
	
	public void tickBuff(Buff buff, float deltaTime){
		buff.time -= deltaTime;
		if (buff.time < 0)
			buffs.remove(buff);
	}

	public LinkedList<Skill> getPassiveSkills(){
		LinkedList<Skill> result = new LinkedList<Skill>();
		for (Skill skill :skills)
			if (skill.type == ActionType.Passive)
				result.add(skill);
		return result;
	}

	public float setDamage(String source, float damage, boolean noLog) {
		if (!noLog)
		Utility.narrate(source + " dealt " + String.valueOf(damage) + " damage to " + name);
		if (!noLog)
			LogMsg.addLog(source + " dealt " + String.valueOf(damage) + " damage to " + name);
		float cdmg = (damage - def)*(100 - prot)/100;
		hp -= (cdmg <=0 )? 1:cdmg;
		if (bal > 0 && damage > 1)
			bal -= rand.nextInt(Math.abs((int) (damage/2))) + damage/2;
		if (hp <= 0){
			Utility.narrate(name + " has been defeated by " + source + "\n");
			isDead = true;
			//if (Game.getInstance().charsAllies.size() == 0 || Game.getInstance().charsEnemies.size() == 0)
			//	Game.getInstance().;
		}
		return cdmg;
		}
	
	public float setDamage(Character source, float damage) {
		float cdmg = (damage - def)*(100 - prot)/100;
		Utility.narrate(source.name + " dealt " + String.valueOf(cdmg) + " damage to " + name);
		LogMsg.addLog(source.name + " dealt " + String.valueOf(cdmg) + " damage to " + name);
		hp -= (cdmg <=0 )? 1:cdmg;
		if (bal > 0 && damage > 1)
			bal -= rand.nextInt(Math.abs((int) (damage/2))) + damage/2;
		if (hp <= 0){
			Utility.narrate(name + " has been defeated by " + source.name + "\n");
			Game.getInstance().removeChar(this);
			isDead = true;
			//if (Game.getInstance().charsAllies.size() == 0 || Game.getInstance().charsEnemies.size() == 0)
				//Game.getInstance().begin();
		}
		return cdmg;
		}

	public void setHeal(Character source, float heal) {
		if (hp + heal < maxHP)
			hp += heal;
		else
			hp = maxHP;
		Utility.narrate(source.name + " healed " + String.valueOf(heal) + " to " + name);
		if (source != this)
			LogMsg.addLog(source.name + " healed " + String.valueOf(heal) + " hp to " + name);
		else
			LogMsg.addLog(source.name + " healed " + String.valueOf(heal) + " hp to " + pronoun4);
	}

	public float getStrDamage() {
		return getStr() / 4;
	}
	
	public float getIntDamage() {
		// TODO Auto-generated method stub
		return getInt() / 4;
	}
	
	
	public Character getTarget() {
		if (target != null)
			return target;
		else 
			if (!isSupporter)
				return Game.getInstance().findEnemy(this);
			else
				return Game.getInstance().findAlly(this);
	}

	public Step getCurrentStep() {
		if (skillCurrent != null && skillCurrent.stepCurrent < skillCurrent.steps.size())
			return skillCurrent.steps.get(skillCurrent.stepCurrent);
		else
			return null;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public void useStamina(float f) {
		sp -= f;
	}
	
	public void useMana(float f) {
		mp -= f;
	}

	public Status getStatus(String string) {
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Status stat: statuses){
			System.out.println(stat.name + " | " +string);
			if (stat.name.equals(string))
				return stat;
		}
		return null;
	}
	
	public boolean isStunned(){
		for (Status stat: statuses)
			if (stat.doStun){
				return true;
			}
		return false;
	}
	
	public Character copy(){
		try {
			return (Character) clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isSameSide(Character character){
		return character.isAllied == isAllied;
	}

	public void reduceBalance(float f) {
		if (bal < f)
			bal = 0;
		else bal -= f;
	}
	
	public LinkedList<Buff> getBuff(Buff.Type type){
		LinkedList<Buff> results = new LinkedList<Buff>();
		for (Buff buff: buffs)
			if (buff.type == type)
				results.add(buff);
		return results;
	}
	
	public float getMaxHP(){
		float result = maxHP;
		for (Buff buff: getBuff(Buff.Type.hp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getHPBuff();
		return result;
	}
	
	public float getMaxMP(){
		float result = maxMP;
		for (Buff buff: getBuff(Buff.Type.mp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getMPBuff();
		return result;
	}
	
	public float getMaxSP(){
		float result = maxSP;
		for (Buff buff: getBuff(Buff.Type.sp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getSPBuff();
		return result;
	}
	
	public float getMaxBal(){
		float result = maxBal;
		for (Buff buff: getBuff(Buff.Type.bal))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getBalBuff();
		return result;
	}
	
	public float getInt(){
		float result = inte;
		for (Buff buff: getBuff(Buff.Type.inte))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getIntBuff();
		return result;
	}
	
	public float getDex(){
		float result = dex;
		for (Buff buff: getBuff(Buff.Type.dex))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getDexBuff();
		return result;
	}
	
	public float getStr(){
		float result = str;
		for (Buff buff: getBuff(Buff.Type.str))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getStrBuff();
		return result;
	}
	
	public float getAgi(){
		float result = dex;
		for (Buff buff: getBuff(Buff.Type.agi))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getAgiBuff();
		return result;
	}
	
	public float getDef(){
		float result = def;
		for (Buff buff: getBuff(Buff.Type.def))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getDefBuff();
		return result;
	}
	
	public float getProt(){
		float result = prot;
		for (Buff buff: getBuff(Buff.Type.prot))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getProtBuff();
		return result;
	}
	
	public float getCP(){
		float result = cpBase;
		for (Skill skill : skills)
			result += skill.getCP();
		result += getMaxHP() + getMaxMP() + getMaxBal()/2 + getMaxSP()/2 + getAgi() + getDex()/2 + getInt() + getStr();
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}


}
