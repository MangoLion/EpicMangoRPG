package com.mangolion.epicmangorpg.characters;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.mangolion.epicmangorpg.ais.AI;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Drop;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Inventory;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.Skills;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.BuffAirborne;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Armor;
import com.mangolion.epicmangorpg.weapons.Weapon;

public class Character implements Cloneable {
	public Character target, summon;
	Random rand = new Random();
	public String name, desc, pronoun = "he", pronoun2 = "him", pronoun3 =  "his", pronoun4 = "himself", gender = " male";
	public Skill skillCurrent, skillCharged;
	public LinkedList<Skill> skills = new LinkedList<Skill>(),
			skillsRecent = new LinkedList<Skill>();
	public LinkedList<Status> statuses = new LinkedList<Status>();
	public LinkedList<Buff> buffs = new LinkedList<Buff>();
	public Weapon weapon;
	public boolean isPlayer = false,
								isAllied = false,
								isSupporter = false,
								isBoss = false,
								isShop = false,
								isDead = false;
	public Armor head, body, legs, feet, robe, hands, accessory;
	public AI ai;
	public Inventory inventory = new Inventory();
	public LinkedList<Drop> drops = new LinkedList<Drop>();
	
	public float str, agi,  inte, dex, maxHP, maxMP, maxSP, maxBal, prot, def, meleeSpeedMod = 1, magicSpeedMod = 1, hpRegen = 0, mpRegen =0.05f, spRegen = 0.1f, balRegen = 0.1f, cpBase = 0 , learnRate = 0, crystals = 0;;
	float hp, mp, sp, bal;
	LinkedList<Element> elements = new LinkedList<Element>();
	public int chargeNum = 0;
	
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
	
	public void equip (ItemCustom item){
		if (item instanceof Weapon)
			equip ((Weapon) item);
			else if (item instanceof Armor)
				equip ((Armor) item);
	}
	
	public void equip (Weapon weapon){
		skillCharged = null;
		this.weapon = weapon;
		if (this instanceof CharacterPlayer && FrameGame.instance != null)
			StylePainter.append(new Msg("$name has equipted $weapon").getMessage(this, null, 0));
		/*for (Skill skill: getSkill(ActionType.WeaponMastery))
			if (skill.checkWeapon(weapon))
				return;*/
		for (Skill skill : Skills.masteries)
			if (skill.checkWeapon(weapon) && !hasSkill(skill)){
				addSkills(Utility.getInstance(skill.getClass()));
				if (this instanceof CharacterPlayer && FrameGame.instance != null)
					StylePainter.append(new Msg("$name has learned " + skill.name).getMessage(this, null, 0));
			}
		
	}
	
	public boolean hasSkill(Skill skill){
		for (Skill s: skills){
			if (s.name.equals(skill.name))
				return true;
		}
		return false;
	}
	
	public void equip (Armor armor){
		switch (armor.type) {
		case Armor.ACCESSORY:
			accessory = armor;
			break;
		case Armor.HEAD:
			head = armor;
			break;
		case Armor.BODY:
			body = armor;
			break;
		case Armor.LEG:
			legs = armor;
			break;
		case Armor.FEET:
			feet = armor;
			break;
		case Armor.ROBE:
			robe = armor;
			break;
		case Armor.HAND:
			hands = armor;
			break;
		default:
			break;
		}
		if (FrameGame.instance != null)
			StylePainter.append(new Msg("$name has equipted " + armor.name).getMessage(this, null, 0));
	}
	
	public void unequipArmor(int type) {
		Armor armor = null;
		switch (type) {
		case Armor.ACCESSORY:
			armor = accessory;
			accessory = null;
		break;
		case Armor.HAND:
			armor = hands;
			hands = null;
		break;
		case Armor.HEAD:
			armor = head;
			head = null;
		break;
		case Armor.FEET:
			armor = feet;
			feet = null;
		break;
		case Armor.LEG:
			armor = legs;
			legs = null;
		break;
		case Armor.ROBE:
			armor = robe;
			robe = null;
		break;
		case Armor.BODY:
			armor = robe;
			robe = null;
		break;

		default:
			break;
		}
		if (FrameGame.instance != null && armor != null)
			StylePainter.append(new Msg("$name has unequipted " + armor.name).getMessage(this, null, 0));
	}
	
	public boolean isEquipted(Armor armor){
		for (Armor a: getArmors())
			if (a == armor)
				return true;
		return false;
	}
	
	public boolean isEquipted(Weapon weapon){
		return weapon == this.weapon;
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
		if (ai != null){
			if (target == null)
				Utility.narrate(name + "couldn't find a target!");
			ai.nextAction();
		}
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
			if (b.name.equals(buff.name) && b.types == buff.types){
				b.time += buff.time;
				return;
			}
		buffs.add(buff);		
	}
	
	public void removeBuff(String name){
		Iterator<Buff> it = buffs.iterator();
		while(it.hasNext())
			if (it.next().name.equals(name))
				it.remove();
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
	
	public Skill getSkill(String name){
		for (Skill skill: skills){
			if (skill.name.equals(name))
				return skill;
		}
		return null;
	}
	
	public LinkedList<Skill> getSkill (ActionType type){
		LinkedList<Skill> result = new LinkedList<Skill>();
		for (Skill skill :skills)
			if (skill.type == type)
				result.add(skill);		
		return result;
	}

	public float setDamage(String source, float damage, boolean noLog) {
		float cdmg = (damage - def)*(100 - prot)/100;
		cdmg = (cdmg <=0 )? 1:cdmg;
		if (!noLog)
		Utility.narrate(source + " dealt " + String.valueOf(cdmg) + " damage to " + name);
		if (!noLog)
			LogMsg.addLog(source + " dealt " + String.valueOf(cdmg) + " damage to " + name);

		hp -= cdmg;
		if (bal > 0 && cdmg > 1)
			bal -= rand.nextInt(Math.abs((int) (cdmg/2))) + cdmg/2;
		if (hp <= 0){
			Utility.narrate(name + " has been defeated by " + source + "\n");
			isDead = true;
			if (!isAllied && source.equals(CharacterPlayer.instance))
				giveDrop();
			//if (Game.getInstance().charsAllies.size() == 0 || Game.getInstance().charsEnemies.size() == 0)
			//	Game.getInstance().;
		}
		return cdmg;
		}
	
	public void giveDrop(){
		for (Drop  drop: drops)
			if (rand.nextFloat() <= drop.chance){
				if (drop.item != null){
					int amount =  rand.nextInt(drop.amount);
					if (amount <= 0)
						amount = 1;
					Utility.narrate("You received " + amount + " " + drop.item.name);
					CharacterPlayer.instance.inventory.addItem(drop.item,amount);
				}
				else{
					Utility.narrate("You received " + drop.itemCustom.name);
					CharacterPlayer.instance.inventory.addItem(drop.itemCustom);
				}
			}
	}
	
	public void addDrop(Item item, float chance, int num){
		drops.add(new Drop(item, chance, num));
		inventory.addItem(item, num);
	}
	
	public void addDrop(ItemCustom item, float chance){
		drops.add(new Drop(item, chance));
		inventory.addItem(item);
	}
	
	public float setDamage(Character source, float damage) {
		float cdmg = (damage - def)*(100 - prot)/100;
		cdmg = (cdmg <=0 )? 1:cdmg;
		Utility.narrate(source.name + " dealt " + String.valueOf(cdmg) + " damage to " + name);
		LogMsg.addLog(source.name + " dealt " + String.valueOf(cdmg) + " damage to " + name);
		hp -= cdmg;
		if (bal > 0 && cdmg > 1)
			bal -=cdmg/2;//rand.nextInt(Math.abs((int) (cdmg/2))) + cdmg/2;
		if (hp <= 0){
			Utility.narrate(name + " has been defeated by " + source.name + "\n");
			Game.getInstance().removeChar(this);
			isDead = true;
			if (!isAllied && source.equals(CharacterPlayer.instance))
				giveDrop();
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
	
	public void changeCrystal(float num){
		crystals = Utility.format(crystals + num);//Math.round(num);
	}
	
	public float getAccuracy(Character target){
		float acc = 0.8f;
		acc+= (getDex()-target.getAgi())*0.02;
		acc += getAccuracyBuff();
		if (acc > 0.95f)
			acc = 0.95f;
		if (acc < 0.25f)
			acc = 0.25f;
			return Utility.format(acc);
	}
	
	public float getCritical(Character target){
		float crit = 0.5f;
		crit += (getAgi()-target.getAgi())*0.02;
		crit += (getDex()-target.getAgi())*0.02;
		crit += getCriticalBuff();
		crit -= target.prot*0.02;
		if (crit < 0.05)
			crit = 0.05f;
		if (crit > 0.95)
			crit = 0.95f;
		return crit;
	}
	
	public LinkedList<Buff> getBuff(Buff.Type type){
		LinkedList<Buff> results = new LinkedList<Buff>();
		for (Buff buff: buffs)
			if (buff.checkType(type))
				results.add(buff);
		return results;
	}
	
	public Armor[] getArmors(){
		Armor armors[]  =  {head, body, legs, feet, robe, hands, accessory};
		return armors;
	}
	
	public boolean isAirborne(){
		boolean result = (getBuff(Buff.Type.airborne).size() > 0);
		return (getBuff(Buff.Type.airborne).size() > 0);
	}
	
	public void setAirborne(float time){
		if (!isAirborne()){
			applyBuff(new BuffAirborne(time));
		}else{
			getBuff(Buff.Type.airborne).getFirst().time = time;
		}
	}
	
	public float getMeleeSpeed(){
		float result = meleeSpeedMod;
		result *= weapon.speedModifier;
		for (Armor armor: getArmors())
			if (armor != null)
			result *= armor.speedModifier;
		return result;
	}
	
	public float getMaxHP(){
		float result = maxHP;
		for (Buff buff: getBuff(Buff.Type.hp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getHPBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getHPBuff();
		return Utility.format(result);
	}
	
	public float getMaxMP(){
		float result = maxMP;
		for (Buff buff: getBuff(Buff.Type.mp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getMPBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getMPBuff();
		return Utility.format(result);
	}
	
	public float getMaxSP(){
		float result = maxSP;
		for (Buff buff: getBuff(Buff.Type.sp))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getSPBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getSPBuff();
		return Utility.format(result);
	}
	
	public float getMaxBal(){
		float result = maxBal;
		for (Buff buff: getBuff(Buff.Type.bal))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getBalBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getBalBuff();
		return Utility.format(result);
	}
	
	public float getInt(){
		float result = inte;
		for (Buff buff: getBuff(Buff.Type.inte))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getIntBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getIntBuff();
		return Utility.format(result);
	}
	
	public float getDex(){
		float result = dex;
		for (Buff buff: getBuff(Buff.Type.dex))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getDexBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getDexBuff();
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
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getStrBuff();
		return Utility.format(result);
	}
	
	public float getAgi(){
		float result = agi;
		for (Buff buff: getBuff(Buff.Type.agi))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getAgiBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getAgiBuff();
		return Utility.format(result);
	}
	
	public float getDef(){
		float result = def;
		for (Buff buff: getBuff(Buff.Type.def))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getDefBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getDefBuff();
		return Utility.format(result);
	}
	
	public float getProt(){
		float result = prot;
		for (Buff buff: getBuff(Buff.Type.prot))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getProtBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getProtBuff();
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
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getHPRegenBuff();
		return Utility.format4( result);
	}
	
	public float getSpRegen() {
		float result = spRegen;
		for (Buff buff: getBuff(Buff.Type.spRegen))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getSPRegenBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getSPRegenBuff();
		return Utility.format4( result);
	}
	
	public float getBalRegen() {
		float result = balRegen;
		for (Buff buff: getBuff(Buff.Type.balRegen))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getBalRegenBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getBalRegenBuff();
		return Utility.format4( result);
	}
	
	public float getMpRegen() {
		float result =mpRegen;
		for (Buff buff: getBuff(Buff.Type.mpRegen))
			result += buff.value;
		for (Skill skill: skills)
			result += skill.getMPRegenBuff();
		for (Armor  armor: getArmors())
			if (armor != null)
				result += armor.getMPRegenBuff();
		return Utility.format4( result);
	}
	
	public float getAccuracyBuff(){
		float acc = 0;
		for (Armor armor: getArmors())
			if (armor != null)
			acc += armor.getAccuracyBuff();
		for (Buff buff: getBuff(Buff.Type.accuracy))
			acc += buff.value;
		acc += weapon.getAccuracyBuff();
		return acc;
	}
	
	public float getCriticalBuff(){
		float crit = 0;
		for (Armor armor: getArmors())
			if (armor != null)
				crit += armor.getCriticalBuff();
		for (Buff buff: getBuff(Buff.Type.crit))
			crit += buff.value;
		crit += weapon.getCriticalBuff();
		return crit;
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
		if (bal < 0)
			bal = 1;
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

	public Character getSummon(){
		if (summon != null && !summon.isDead)
			return summon;
		return null;
	}
	
	public void openShop(){
		
	}


}
