package com.mangolion.epicmangorpg.characters;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.rmi.CORBA.Util;

import com.mangolion.epicmangorpg.ais.AI;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Inventory;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.Skills;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class Character implements Cloneable {
	public Character target;
	Random rand = new Random();
	public String name, desc, pronoun = "he", pronoun2 = "him", pronoun3 =  "his", pronoun4 = "himself", gender = " male";
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

	public float str, agi,  inte, dex, maxHP, maxMP, maxSP, maxBal, prot, def, meleeSpeedMod = 1, magicSpeedMod = 1, hpRegen = 0, mpRegen =0.05f, spRegen = 0.1f, balRegen = 0.1f, cpBase = 0 , learnRate = 0;;
	float hp, mp, sp, bal;
	LinkedList<Element> elements = new LinkedList<Element>();
	
	public LinkedList<Element> getElements(){
		LinkedList<Element> results = new LinkedList<Element>();
		results.addAll(elements);
		for (Buff buff : getBuff(Buff.Type.elemental))
			results.add(buff.element);
		return results;
	}


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
		equip(weapon);
	}

	public void addSkills(Skill... skills) {
		if (skills != null) {
			this.skills.addAll(Arrays.asList(skills));
			for (Skill skill : skills)
				skill.character = this;
		}
	}
	
	public void addElements(Element... elements) {
		if (elements != null) {
			this.elements.addAll(Arrays.asList(elements));
		}
	}
	
	public void equip (Weapon weapon){
		this.weapon = weapon;

		for (Skill skill: getSkill(ActionType.WeaponMastery))
			if (skill.checkWeapon(weapon))
				return;
		for (Skill skill : Skills.masteries)
			if (skill.checkWeapon(weapon)){
				addSkills(Utility.getInstance(skill.getClass()));
				if (this instanceof CharacterPlayer && FrameGame.instance != null)
					StylePainter.append(new Msg("$name has learned " + skill.name).getMessage(this, null, 0));
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
		Iterator<Buff> it = buffs.iterator();
		while (it.hasNext()){
			Buff buff = it.next();
			buff.time -=  deltaTime;
			if (buff.time <= 0){
				StylePainter.append(new Msg("$name is no longer " + name).getMessage(this, null, 0));
				it.remove();
			}
		}
		if (skillCurrent == null && Game.getInstance().findTick(this) == null && !isStunned())
			nextAction();
		else if(skillCurrent != null && Game.game.findTick(this) == null)
			skillCurrent.execute();
		regen(deltaTime);
	}
	
	public void regen(float deltaTime){
		if (bal < getMaxBal())
			bal += Math.round(deltaTime*getMaxBal()*getBalRegen()*100f)/100f;
		if (sp < getMaxSP() && (skillCurrent == null || (skillCurrent != null && !skillCurrent.isLoading)))
			sp += Math.round(deltaTime*getMaxSP()*getSpRegen()*100f)/100f;
		if (hp < getMaxHP())
			hp += Math.round(deltaTime*getMaxHP()*getHpRegen()*100f)/100f;
		if (mp < getMaxMP())
			mp += Math.round(deltaTime*getMaxMP()*getMpRegen()*100f)/100f;
	}
	
	public void applyBuff(Buff buff){
		for (Buff b: buffs)
			if (b.name.equals(buff.name) && b.type == buff.type){
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
			if (skill.type.getGeneralType() == GeneralType.Passive)
				result.add(skill);		
		return result;
	}
	
	public LinkedList<Skill> getSkill (ActionType type){
		LinkedList<Skill> result = new LinkedList<Skill>();
		for (Skill skill :skills)
			if (skill.type == type)
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
		if (hp + heal < getMaxHP())
			hp += heal;
		else
			hp = getMaxHP();
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
	
	public void scale(float scale){
		maxHP *= scale;
		maxMP*= scale;
		maxSP  *= scale;
		maxBal  *= scale;
		hp = maxHP;
		mp = maxMP;
		sp = maxSP;
		bal = maxBal;
		str  *= scale;
		inte  *= scale;
		dex  *= scale;
		def  *= scale;
		prot  *= scale;
		agi  *= scale;
		
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
		return Utility.format(result);
	}
	
	public float getMaxMP(){
		float result = maxMP;
		for (Buff buff: getBuff(Buff.Type.mp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getMPBuff();
		return Utility.format(result);
	}
	
	public float getMaxSP(){
		float result = maxSP;
		for (Buff buff: getBuff(Buff.Type.sp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getSPBuff();
		return Utility.format(result);
	}
	
	public float getMaxBal(){
		float result = maxBal;
		for (Buff buff: getBuff(Buff.Type.bal))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getBalBuff();
		return Utility.format(result);
	}
	
	public float getInt(){
		float result = inte;
		for (Buff buff: getBuff(Buff.Type.inte))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getIntBuff();
		return Utility.format(result);
	}
	
	public float getDex(){
		float result = dex;
		for (Buff buff: getBuff(Buff.Type.dex))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getDexBuff();
		return Utility.format(result);
	}
	
	public float getStr(){
		float result = str;
		for (Buff buff: getBuff(Buff.Type.str)){
			result += buff.value;
		//	System.out.println(buff.name + " " + buff.value);
		}
		for (Skill skill: skills)
			result += skill.getStrBuff();
		return Utility.format(result);
	}
	
	public float getAgi(){
		float result = dex;
		for (Buff buff: getBuff(Buff.Type.agi))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getAgiBuff();
		return Utility.format(result);
	}
	
	public float getDef(){
		float result = def;
		for (Buff buff: getBuff(Buff.Type.def))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getDefBuff();
		return Utility.format(result);
	}
	
	public float getProt(){
		float result = prot;
		for (Buff buff: getBuff(Buff.Type.prot))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getProtBuff();
		return Utility.format(result);
	}
	
	public float getCP(){
		if (mp == 0)
			mp = hp * 0.7f;
		float result = cpBase;
		for (Skill skill : skills)
			result += skill.getCP();
		result += getMaxHP() + getMaxMP() + getMaxBal()/2 + getMaxSP()/2 + getAgi() + getDex()/2 + getInt() + getStr();
		return Utility.format(result);
	}
	
	public float getHpRegen() {
		float result = hpRegen;
		for (Buff buff: getBuff(Buff.Type.hpRegen))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getHPRegenBuff();
		return Utility.format4( result);
	}
	
	public float getSpRegen() {
		float result = spRegen;
		for (Buff buff: getBuff(Buff.Type.spRegen))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getSPRegenBuff();
		return Utility.format4( result);
	}
	
	public float getBalRegen() {
		float result = balRegen;
		for (Buff buff: getBuff(Buff.Type.balRegen))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getBalRegenBuff();
		return Utility.format4( result);
	}
	
	public float getMpRegen() {
		float result =mpRegen;
		for (Buff buff: getBuff(Buff.Type.mpRegen))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getMPRegenBuff();
		return Utility.format4( result);
	}
	
	
	public float getHp() {
		return Utility.format( hp);
	}

	public void addHp(float hp) {
		this.hp = hp;
	}

	public float getMp() {
		return Utility.format( mp);
	}

	public void addMp(float mp) {
		this.mp = mp;
	}

	public float getSp() {
		return Utility.format( sp);
	}

	public void addSp(float sp) {
		this.sp = sp;
	}

	public float getBal() {
		return Utility.format( bal);
	}

	public void addBal(float bal) {
		this.bal = bal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}


}
