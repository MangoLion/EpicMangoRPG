package com.mangolion.epicmangorpg.characters;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.mangolion.epicmangorpg.ais.AI;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Damage;
import com.mangolion.epicmangorpg.components.Drop;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.Style;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Inventory;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.ItemStack;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillWait;
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
	public String name, desc, pronoun = "he", pronoun2 = "him",
			pronoun3 = "his", pronoun4 = "himself", gender = " male";
	public Skill skillCurrent, skillCharged;
	public LinkedList<Skill> skills = new LinkedList<Skill>(),
			skillsRecent = new LinkedList<Skill>();
	public LinkedList<Status> statuses = new LinkedList<Status>();
	public LinkedList<Buff> buffs = new LinkedList<Buff>();
	public Weapon weapon;
	public boolean isPlayer = false, isAllied = false, isSupporter = false,
			isBoss = false, isShop = false, isDead = false;
	public Armor head, body, legs, feet, robe, hands, accessory;
	public AI ai;
	public Inventory inventory = new Inventory();
	public LinkedList<Drop> drops = new LinkedList<Drop>();
	public LinkedList<Barrier> barriers = new LinkedList<Barrier>();
	protected float str, agi, inte, dex, maxHP, maxMP, maxSP, maxBal, prot,
			def, magicDef, meleeSpeedMod = 1, magicSpeedMod = 1, hpRegen = 0,
			mpRegen = 0.05f, spRegen = 0.1f, balRegen = 0.1f, cpBase = 0,
			crystals = 0;
	public float style = 10;
	public float learnRate = 0;
	float hp, mp, sp, bal;
	LinkedList<Element> elements = new LinkedList<Element>();
	public int chargeNum = 0;

	public LinkedList<Element> getElements() {
		LinkedList<Element> results = new LinkedList<Element>();
		results.addAll(elements);
		for (Buff buff : buffs)
			if (buff.element != null)
				results.add(buff.element);
		return results;
	}

	public void reset() {
		this.hp = getMaxHP();
		this.mp = getMaxMP();
		this.sp = getMaxSP();
		this.bal = getMaxBal();
		statuses = new LinkedList<Status>();
		style = 10;
		summon = null;
		buffs.clear();
		skillCharged = null;
		buffs.clear();
		for (Skill skill : skills)
			skill.reset();
	}

	public Character(String name, String desc, float hp, float mp, float stam,
			float str, float agi, float bal, float inte, float dex, float def,
			float prot, Weapon weapons, Skill... skills) {
		this.name = name;
		maxHP = hp;
		maxMP = mp;
		maxSP = stam;
		maxBal = bal;
		this.hp = maxHP;
		this.mp = maxMP;
		this.sp = maxSP;
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
		//
		addSkills(new SkillRespite());
		addSkills(new SkillWait());
		equip(weapon);
		// barriers.add(new Barrier(this, "Fire Barrier", 100, 0, 0, 100, 0.7f,
		// new Element("Fire", 1)));
	}

	public void addBarrier(Barrier barrier) {
		for (Barrier b : barriers)
			if (b.name.equalsIgnoreCase(barrier.name)) {
				b.time += barrier.time;
				b.hp = barrier.hp;
				return;
			}
		barriers.add(barrier);
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

	public void equip(ItemCustom item) {
		if (item instanceof Weapon)
			equip((Weapon) item);
		else if (item instanceof Armor)
			equip((Armor) item);
	}

	public void changeStyle(float change) {
		style += change;
		if (style < 0)
			style = 0;
		if (style > 20)
			style = 20;

		System.out.println(name + " style change by " + change);
	}

	public void equip(Weapon weapon) {
		skillCharged = null;
		this.weapon = weapon;
		if (this instanceof CharacterPlayer && FrameGame.instance != null)
			StylePainter.append(new Msg("$name has equipted $weapon")
					.getMessage(this, null, 0));
		/*
		 * for (Skill skill: getSkill(ActionType.WeaponMastery)) if
		 * (skill.checkWeapon(weapon)) return;
		 */
		for (Skill skill : Skills.masteries)
			if (skill.checkWeapon(weapon) && !hasSkill(skill)) {
				addSkills(Utility.getInstance(skill.getClass()));
				if (this instanceof CharacterPlayer
						&& FrameGame.instance != null)
					StylePainter.append(new Msg("$name has learned "
							+ skill.name).getMessage(this, null, 0));
			}

	}

	public boolean hasSkill(Skill skill) {
		for (Skill s : skills) {
			if (s.name.equals(skill.name))
				return true;
		}
		return false;
	}

	public void equip(Armor armor) {
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
			StylePainter.append(new Msg("$name has equipted " + armor.name)
					.getMessage(this, null, 0));
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
			StylePainter.append(new Msg("$name has unequipted " + armor.name)
					.getMessage(this, null, 0));
	}

	public boolean isEquipted(Armor armor) {
		for (Armor a : getArmors())
			if (a == armor)
				return true;
		return false;
	}

	public boolean isEquipted(Weapon weapon) {
		return weapon == this.weapon;
	}

	public void addStatus(Status stat) {
		StylePainter.append(new Msg(name + " is " + stat.name
				+ " for $num seconds").getMessage(null, null, stat.time));
		for (Status status : statuses)
			if (status.name.equals(stat.name)) {
				status.multiplier = stat.multiplier;
				status.time += stat.time;
				return;
			}
		statuses.add(stat);
	}

	public void nextAction() {
		if (isPlayer) {
			if (!isStunned())
				Game.getInstance().timer.stop();
			return;
		}
		if (target == null || (target != null && target.isDead))
			if (!isSupporter)
				target = Game.getInstance().findEnemy(this);
			else
				target = Game.getInstance().findAlly(this);
		if (ai != null) {
			if (target == null)
				Utility.narrate(name + "couldn't find a target!");
			ai.nextAction();
		}
	}

	public void tick(float deltaTime) {
		Iterator<Barrier> ib = barriers.iterator();
		while (ib.hasNext()) {
			Barrier barrier = ib.next();
			barrier.time -= deltaTime;
			if (barrier.time <= 0) {
				StylePainter.append(new Msg("$name's " + barrier.name
						+ " has disappeared").getMessage(this, null, 0));
				ib.remove();
			}
		}
		Iterator<Status> i = statuses.iterator();
		while (i.hasNext()) {
			Status status = i.next();
			status.tick(deltaTime);
			if (status.time <= 0) {
				status.remove();
				i.remove();
			}
		}
		Iterator<Buff> it = buffs.iterator();
		while (it.hasNext()) {
			Buff buff = it.next();
			buff.time -= deltaTime;
			if (buff.time <= 0) {
				if (buff.notify)
					StylePainter
							.append(new Msg("$name's " + buff.name
									+ " buff has disapeared").getMessage(this,
									null, 0));
				it.remove();
			}
		}
		if (isDead)
			return;

		if (skillCurrent == null && !isStunned())
			nextAction();
		else if (skillCurrent != null)
			if (skillCurrent.tick <= 0)
				skillCurrent.execute();
			else
				skillCurrent.tick = Utility.format(skillCurrent.tick - deltaTime);
		regen(deltaTime);
	}

	public void regen(float deltaTime) {
		if (bal < getMaxBal())
			bal += Math.round(deltaTime * getMaxBal() * getBalRegen() * 100f) / 100f;
		if (sp < getMaxSP()
				&& (skillCurrent == null || (skillCurrent != null && !skillCurrent.isLoading)))
			sp += Math.round(deltaTime * getMaxSP() * getSpRegen() * 100f) / 100f;
		if (hp < getMaxHP())
			hp += Math.round(deltaTime * getMaxHP() * getHpRegen() * 100f) / 100f;
		if (mp < getMaxMP())
			mp += Math.round(deltaTime * getMaxMP() * getMpRegen() * 100f) / 100f;
	}

	public void applyBuff(Buff buff) {
		for (Buff b : buffs)
			if (b.name.equalsIgnoreCase(buff.name)) {
				b.time += buff.time;
				return;
			}
		buffs.add(buff);
	}

	public void removeBuff(String name) {
		Iterator<Buff> it = buffs.iterator();
		while (it.hasNext())
			if (it.next().name.equals(name))
				it.remove();
	}

	public void tickBuff(Buff buff, float deltaTime) {
		buff.time -= deltaTime;
		if (buff.time < 0)
			buffs.remove(buff);
	}

	public LinkedList<Skill> getPassiveSkills() {
		LinkedList<Skill> result = new LinkedList<Skill>();
		for (Skill skill : skills)
			if (skill.type.getGeneralType() == GeneralType.Passive)
				result.add(skill);
		return result;
	}

	public Skill getSkill(String name) {
		for (Skill skill : skills) {
			if (skill.name.equalsIgnoreCase(name))
				return skill;
		}
		return null;
	}

	public LinkedList<Skill> getSkill(GeneralType type) {
		LinkedList<Skill> result = new LinkedList<Skill>();
		for (Skill skill : skills)
			if (skill.type.getGeneralType() == type)
				result.add(skill);
		return result;
	}

	public LinkedList<Skill> getSkill(ActionType type) {
		LinkedList<Skill> result = new LinkedList<Skill>();
		for (Skill skill : skills)
			if (skill.type == type)
				result.add(skill);
		return result;
	}

	public float setDamage(String source, float damage, boolean noLog) {
		float cdmg;

		cdmg = (damage - def) * (100 - prot) / 100;
		cdmg = (cdmg <= 0) ? 1 : cdmg;
		if (!noLog) {
			String style = "";
			Character c = Game.getInstance().getCharacter(source);
			if (c != null) {
				Style.positive(c, this, Style.dmg, damage);
				style = "[" + Style.getMsg(c.style) + "]";
			}
			Utility.narrate(source + " dealt " + String.valueOf(cdmg)
					+ " damage to " + name + " " + style);

			LogMsg.addLog(source + " dealt " + String.valueOf(cdmg)
					+ " damage to " + name);
		}

		hp -= cdmg;
		if (bal > 0 && cdmg > 1)
			bal -= rand.nextInt(Math.abs((int) (cdmg / 2))) + cdmg / 2;
		if (hp <= 0) {
			Utility.narrate(name + " has been defeated by " + source + "\n");
			Game.getInstance().removeChar(this);
			isDead = true;
			if (!isAllied && source.equals(CharacterPlayer.instance))
				giveDrop();
		}
		return cdmg;
	}

	public void giveDrop() {
		for (Drop drop : drops)
			if (rand.nextFloat() <= drop.chance) {
				if (drop.item != null) {
					int amount = rand.nextInt(drop.amount);
					if (amount <= 0)
						amount = 1;
					Utility.narrate("You received " + amount + " "
							+ drop.item.name);
					CharacterPlayer.instance.inventory.addItem(drop.item,
							amount);
				} else {
					Utility.narrate("You received " + drop.itemCustom.name);
					CharacterPlayer.instance.inventory.addItem(drop.itemCustom);
				}
			}
	}

	public void addDrop(Item item, float chance, int num) {
		drops.add(new Drop(item, chance, num));
		inventory.addItem(item, num);
	}

	public void addDrop(ItemCustom item, float chance) {
		drops.add(new Drop(item, chance));
		inventory.addItem(item);
	}

	public float setDamage(Damage damage) {
		Character source = damage.source;

		Iterator<Barrier> it = barriers.iterator();
		while (it.hasNext()) {
			Barrier barrier = it.next();
			barrier.setDamage(damage);
			if (barrier.hp <= 0) {
				StylePainter.append(new Msg("$name's " + barrier.name
						+ " has been broken!").getMessage(this, null, 0));
				it.remove();
			}
		}

		float cdmg, eleMult = Elements
				.calculate(damage.elements, getElements());
		;
		if (damage.type == Damage.MELEE || damage.type == Damage.RANGE)
			cdmg = (damage.amount - def) * (100 - prot) / 100;
		else
			cdmg = (damage.amount - magicDef) * (100 - prot) / 100;
		cdmg *= eleMult;

		if (rand.nextFloat() <= eleMult / 2) {
			if (damage.statuses.size() > 0)
				for (Status status : damage.statuses) {
					status.character = this;
					addStatus(status.copy());
				}
			if (damage.buffs.size() > 0)
				for (Buff buff : damage.buffs) {
					applyBuff(buff);
				}
		}

		cdmg = (cdmg <= 0) ? 1 : cdmg;
		float change = Style.positive(source, this, Style.dmg, cdmg,
				1 - source.getAccuracy(this));
		StylePainter.append(new Msg(false,
				"$name dealt $num damage to $targetname").getMessage(source,
				this, cdmg), Style.getSegments(change, source));
		LogMsg.addLog(source + " dealt " + String.valueOf(cdmg) + " damage to "
				+ name);
		hp -= cdmg;
		if (bal > 0 && cdmg > 1)
			bal -= cdmg;
		if (hp <= 0) {
			Utility.narrate(name + " has been defeated by " + source.name
					+ "\n");
			Game.getInstance().removeChar(this);
			isDead = true;
			if (!isAllied && source.equals(CharacterPlayer.instance))
				giveDrop();
		}
		return cdmg;
	}

	public void setHeal(Character source, float heal) {
		if (hp + heal < getMaxHP())
			hp += heal;
		else
			hp = getMaxHP();
		Utility.narrate(source.name + " healed " + String.valueOf(heal)
				+ " to " + name);
		if (source != this)
			LogMsg.addLog(source.name + " healed " + String.valueOf(heal)
					+ " hp to " + name);
		else
			LogMsg.addLog(source.name + " healed " + String.valueOf(heal)
					+ " hp to " + pronoun4);
	}

	public boolean isEquipted(ItemCustom item) {
		if (item instanceof Weapon)
			return isEquipted((Weapon) item);
		if (item instanceof Armor)
			return isEquipted((Armor) item);
		return false;
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
		else if (!isSupporter)
			return Game.getInstance().findEnemy(this);
		else
			return Game.getInstance().findAlly(this);
	}

	public Step getCurrentStep() {
		if (skillCurrent != null
				&& skillCurrent.stepCurrent < skillCurrent.steps.size())
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
		for (Status stat : statuses) {
			if (stat.name.equals(string))
				return stat;
		}
		return null;
	}

	public boolean isStunned() {
		for (Status stat : statuses)
			if (stat.doStun) {
				return true;
			}
		return false;
	}

	public Character copy() {
		try {
			return (Character) clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean isSameSide(Character character) {
		return character.isAllied == isAllied;
	}

	public void reduceBalance(float f) {
		if (bal < f)
			bal = 0;
		else
			bal -= f;
	}

	public void scale(float scale) {
		maxHP *= scale;
		maxMP *= scale;
		maxSP *= scale;
		maxBal *= scale;
		hp = maxHP;
		mp = maxMP;
		sp = maxSP;
		bal = maxBal;
		str *= scale;
		inte *= scale;
		dex *= scale;
		def *= scale;
		prot *= scale;
		agi *= scale;

	}

	public void changeCrystal(float num) {
		crystals = Utility.format(crystals + num);// Math.round(num);
	}

	public float getAccuracy(Character target) {
		float acc = 0.8f;
		acc += (getDex() - target.getAgi()) * 0.02;
		acc += getAccuracyBuff();
		if (acc > 0.95f)
			acc = 0.95f;
		if (acc < 0.25f)
			acc = 0.25f;
		return Utility.format(acc);
	}

	public float getCritical(Character target) {
		float crit = 0.5f;
		crit += (getAgi() - target.getAgi()) * 0.02;
		crit += (getDex() - target.getAgi()) * 0.02;
		crit += getCriticalBuff();
		crit -= target.prot * 0.02;
		if (crit < 0.05)
			crit = 0.05f;
		if (crit > 0.95)
			crit = 0.95f;
		return crit;
	}

	public LinkedList<Buff> getBuff(Buff.Type type) {
		LinkedList<Buff> results = new LinkedList<Buff>();
		for (Buff buff : buffs)
			if (buff.checkType(type))
				results.add(buff);
		return results;
	}

	public Armor[] getArmors() {
		Armor armors[] = { head, body, legs, feet, robe, hands, accessory };
		return armors;
	}

	public boolean isAirborne() {
		boolean result = (getBuff(Buff.Type.airborne).size() > 0);
		return (getBuff(Buff.Type.airborne).size() > 0);
	}

	public void setAirborne(float time) {
		if (!isAirborne()) {
			applyBuff(new BuffAirborne(time));
		} else {
			getBuff(Buff.Type.airborne).getFirst().time = time;
		}
	}

	public float getMeleeSpeed() {
		float result = meleeSpeedMod;
		result *= weapon.speedModifier;
		for (Armor armor : getArmors())
			if (armor != null)
				result *= armor.speedModifier;
		return result;
	}

	public float getMaxHP() {
		float result = maxHP;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.hp))
			mult += buff.getValue(Buff.Type.hp);
		for (Barrier barrier : barriers)
			result += barrier.getHPBuff();
		for (Skill skill : skills)
			result += skill.getHPBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getHPBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getMaxMP() {
		float result = maxMP;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.mp))
			mult += buff.getValue(Buff.Type.mp);
		for (Barrier barrier : barriers)
			result += barrier.getMPBuff();
		for (Skill skill : skills)
			result += skill.getMPBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getMPBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getMaxSP() {
		float result = maxSP;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.sp))
			mult += buff.getValue(Buff.Type.sp);
		for (Barrier barrier : barriers)
			result += barrier.getSPBuff();
		for (Skill skill : skills)
			result += skill.getSPBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getSPBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getMaxBal() {
		float result = maxBal;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.bal))
			mult += buff.getValue(Buff.Type.bal);
		for (Barrier barrier : barriers)
			result += barrier.getBalBuff();
		for (Skill skill : skills)
			result += skill.getBalBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getBalBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getInt() {
		float result = inte;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.inte))
			mult += buff.getValue(Buff.Type.inte);
		for (Barrier barrier : barriers)
			result += barrier.getIntBuff();
		for (Skill skill : skills)
			result += skill.getIntBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getIntBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getDex() {
		float result = dex;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.dex))
			mult += buff.getValue(Buff.Type.dex);
		for (Barrier barrier : barriers)
			result += barrier.getDexBuff();
		for (Skill skill : skills)
			result += skill.getDexBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getDexBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getStr() {
		float result = str;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.str))
			mult += buff.getValue(Buff.Type.str);
		for (Barrier barrier : barriers)
			result += barrier.getStrBuff();
		for (Skill skill : skills)
			result += skill.getStrBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getStrBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getAgi() {
		float result = agi;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.agi))
			mult += buff.getValue(Buff.Type.agi);
		for (Barrier barrier : barriers)
			result += barrier.getAgiBuff();
		for (Skill skill : skills)
			result += skill.getAgiBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getAgiBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getDef() {
		float result = def;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.def))
			mult += buff.getValue(Buff.Type.def);
		for (Barrier barrier : barriers)
			result += barrier.getDefBuff();
		for (Skill skill : skills)
			result += skill.getDefBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getDefBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getMagicDef() {
		float result = magicDef;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.magicDef))
			mult += buff.getValue(Buff.Type.magicDef);
		for (Barrier barrier : barriers)
			result += barrier.getMagicDefBuff();
		for (Skill skill : skills)
			result += skill.getMagicDefBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getMagicDefBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getProt() {
		float result = prot;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.prot))
			mult += buff.getValue(Buff.Type.prot);
		for (Barrier barrier : barriers)
			result += barrier.getProtBuff();
		for (Skill skill : skills)
			result += skill.getProtBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getProtBuff();
		result *= mult;
		return Utility.format(result);
	}

	public float getBarrierNegate() {
		float result = 0;
		for (Buff buff : getBuff(Buff.Type.barrierNegate))
			result += buff.getValue(Buff.Type.barrierNegate);
		for (Barrier barrier : barriers)
			result += barrier.getBarrierNegate();
		for (Skill skill : skills)
			result += skill.getBarrierNegate();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getBarrierNegate();
		return Utility.format(result);
	}

	public float getCP() {
		if (mp == 0)
			mp = hp * 0.7f;
		float result = cpBase;
		for (Skill skill : skills)
			result += skill.getCP();
		result += getMaxHP() + getMaxMP() + getMaxBal() / 2 + getMaxSP() / 2
				+ getAgi() * 2 + getDex() / 2 + getInt() * 1.5f + getStr()
				* 1.5f;
		return Utility.format(result);
	}

	public float getHpRegen() {
		float result = hpRegen;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.hpRegen))
			mult += buff.getValue(Buff.Type.hpRegen);
		for (Barrier barrier : barriers)
			result += barrier.getHPRegenBuff();
		for (Skill skill : skills)
			result += skill.getHPRegenBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getHPRegenBuff();
		result *= mult;
		return Utility.format4(result);
	}

	public float getSpRegen() {
		float result = spRegen;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.spRegen))
			mult += buff.getValue(Buff.Type.spRegen);
		for (Barrier barrier : barriers)
			result += barrier.getSPRegenBuff();
		for (Skill skill : skills)
			result += skill.getSPRegenBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getSPRegenBuff();
		result *= mult;
		return Utility.format4(result);
	}

	public float getBalRegen() {
		float result = balRegen;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.balRegen))
			mult += buff.getValue(Buff.Type.balRegen);
		for (Barrier barrier : barriers)
			result += barrier.getBalRegenBuff();
		for (Skill skill : skills)
			result += skill.getBalRegenBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getBalRegenBuff();
		result *= mult;
		return Utility.format4(result);
	}

	public float getMpRegen() {
		float result = mpRegen;
		float mult = 1;
		for (Buff buff : getBuff(Buff.Type.mpRegen))
			mult += buff.getValue(Buff.Type.mpRegen);
		for (Barrier barrier : barriers)
			result += barrier.getMPRegenBuff();
		for (Skill skill : skills)
			result += skill.getMPRegenBuff();
		for (Armor armor : getArmors())
			if (armor != null)
				result += armor.getMPRegenBuff();
		result *= mult;
		return Utility.format4(result);
	}

	public float getBuffValue(Buff.Type type) {
		float result = 0;
		for (Buff buff : getBuff(type))
			result += buff.getValue(type);
		return result;
	}

	public float getAccuracyBuff() {
		float acc = 0;
		for (Armor armor : getArmors())
			if (armor != null)
				acc += armor.getAccuracyBuff();
		for (Buff buff : getBuff(Buff.Type.accuracy))
			acc += buff.getValue(Buff.Type.accuracy);
		acc += weapon.getAccuracyBuff();
		return acc;
	}

	public float getCriticalBuff() {
		float crit = 0;
		for (Armor armor : getArmors())
			if (armor != null)
				crit += armor.getCriticalBuff();
		for (Buff buff : getBuff(Buff.Type.crit))
			crit += buff.getValue(Buff.Type.crit);
		crit += weapon.getCriticalBuff();
		return crit;
	}

	public float getHpCost() {
		float cost = 1;
		float mult = 1;
		for (Armor armor : getArmors())
			if (armor != null)
				cost += armor.getHPCostBuff();
		for (Buff buff : getBuff(Buff.Type.hpCost))
			mult += buff.getValue(Buff.Type.hpCost);
		cost += weapon.getHPCostBuff();
		cost *= mult;
		return cost;
	}

	public float getMpCost() {
		float cost = 1;
		float mult = 1;
		for (Armor armor : getArmors())
			if (armor != null)
				cost += armor.getMPCostBuff();
		for (Buff buff : getBuff(Buff.Type.mpCost))
			mult += buff.getValue(Buff.Type.mpCost);
		cost += weapon.getMPCostBuff();
		cost *= mult;
		return cost;
	}

	public float getSpCost() {
		float cost = 1;
		float mult = 1;
		for (Armor armor : getArmors())
			if (armor != null)
				cost += armor.getSPCostBuff();
		for (Buff buff : getBuff(Buff.Type.spCost))
			mult += buff.getValue(Buff.Type.spCost);
		cost += weapon.getSPCostBuff();
		cost *= mult;
		return cost;
	}

	public float getBalCost() {
		float cost = 1;
		float mult = 1;
		for (Armor armor : getArmors())
			if (armor != null)
				cost += armor.getBalCostBuff();
		for (Buff buff : getBuff(Buff.Type.balCost))
			mult += buff.getValue(Buff.Type.balCost);
		cost += weapon.getBalCostBuff();
		cost *= mult;
		return cost;
	}

	public float getHp() {
		return Utility.format(hp);
	}

	public void addHp(float hp) {
		this.hp = hp;
	}

	public float getMp() {
		return Utility.format(mp);
	}

	public void addMp(float mp) {
		this.mp = mp;
	}

	public float getSp() {
		return Utility.format(sp);
	}

	public void addSp(float sp) {
		this.sp = sp;
	}

	public float getBal() {
		if (bal < 0)
			bal = 1;
		return Utility.format(bal);
	}

	public void addBal(float bal) {
		this.bal = bal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	public Character getSummon() {
		if (summon != null && !summon.isDead)
			return summon;
		return null;
	}

	public void openShop() {

	}

	public float getCrystals() {
		// TODO Auto-generated method stub
		return crystals;
	}

	public Wini toWini(File file) throws InvalidFileFormatException,
			IOException {
		Wini wini = new Wini(file);
		wini.put("general info", "Name", name);
		wini.put("general info", "Gender", gender);
		wini.put("general info", "hp", maxHP);
		wini.put("general info", "mp", maxMP);
		wini.put("general info", "sp", maxSP);
		wini.put("general info", "bal", maxBal);
		wini.put("general info", "str", str);
		wini.put("general info", "dex", dex);
		wini.put("general info", "int", inte);
		wini.put("general info", "agi", agi);
		wini.put("general info", "def", def);
		wini.put("general info", "prot", prot);
		wini.put("general info", "crystal", crystals);

		String str = "";
		for (ItemStack stack : inventory.itemStacks)
			str += stack.item.name + "|" + stack.stack + "|";
		wini.put("Inventory", "Consumables", str);

		str = "";
		for (ItemCustom item : inventory.itemCustoms) {
			str += item.name + "|";
			wini.put(item.name, "durability", item.durability);
			wini.put(item.name, "isEquipted", isEquipted(item));
			for (int i = 0; i < item.valueNum; i++)
				wini.put(item.name, "value " + i, item.values.get(i));
		}
		wini.put("Inventory", "ItemCustoms", str);

		String skills = "";
		for (Skill skill : this.skills)
			skills += skill.name + "|";
		wini.put("general info", "skills", skills);

		for (Skill skill : this.skills) {
			wini.put(skill.name, "prof", skill.prof);
			for (Step step : skill.steps)
				wini.put(skill.name, "Step " + skill.steps.indexOf(step),
						step.prof);
		}
		return wini;
	}

	public void loadWini(Wini wini) {
		gender = wini.get("general info", "Gender");
		maxHP = wini.get("general info", "hp", Float.class);
		maxMP = wini.get("general info", "mp", Float.class);
		maxSP = wini.get("general info", "sp", Float.class);
		maxBal = wini.get("general info", "bal", Float.class);
		str = wini.get("general info", "str", Float.class);
		dex = wini.get("general info", "dex", Float.class);
		inte = wini.get("general info", "int", Float.class);
		agi = wini.get("general info", "agi", Float.class);
		def = wini.get("general info", "def", Float.class);
		prot = wini.get("general info", "prot", Float.class);
		crystals = wini.get("general info", "crystal", Float.class);

		String items = wini.get("Inventory", "Consumables");

		boolean getStack = false;
		Item item = null;
		for (String str : items.split("\\|")) {
			if (!getStack) {
				item = Items.getItem(str);
				if (item != null)
					getStack = true;

			} else {
				int stack = Integer.valueOf(str);
				getStack = false;
				inventory.addItem(item, stack);
			}
		}

		String skills = wini.get("general info", "skills");
		for (String str : skills.split("\\|")) {
			Skill skill = Skills.getSkill(str);
			if (skill == null)
				continue;
			skill.prof = wini.get(skill.name, "prof", Float.class);
			for (int i = 0; i < skill.steps.size(); i++) {
				Step step = skill.steps.get(i);
				step.prof = wini.get(skill.name, "Step " + i, Float.class);
			}
			addSkills(skill);
		}

		items = wini.get("Inventory", "ItemCustoms");
		for (String str : items.split("\\|")) {
			ItemCustom itemC = Items.getItemCustom(str);
			if (itemC != null)
				itemC = Utility.getInstance(itemC.getClass());
			else
				continue;
			// System.out.println(name + " "+ item.name);
			float dur = wini.get(itemC.name, "durability", Float.class);
			itemC.durability = dur;
			inventory.addItem(itemC);
			for (int i = 0; i < itemC.valueNum; i++)
				itemC.values.add(wini.get(itemC.name, "value " + i));
			if (wini.get(itemC.name, "isEquipted", Boolean.class)) {
				equip(itemC);
			}
		}
	}
}
