package com.mangolion.epicmangorpg.ais;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Damage;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapon;

public class AISimple extends AI {

	public float chanceDefend = 0.5f, chanceRecover = 0.5f;

	public AISimple(Character character) {
		super(character);
		chanceDefend = 0.4f;
	}

	public AISimple(Character character, float chanceDefend, float chanceRecover) {
		super(character);
		this.chanceDefend = chanceDefend;
		this.chanceRecover = chanceRecover;
	}

	public float recoverLevel = 0.4f;

	@Override
	public void nextAction() {
		if (character.target == null) {
			// Utility.narrate(character.name +
			// " decided to stay idle this turn");
			character.ai = null;
			return;
		}
		if (!checkDefense())
			if (!checkBarrier())
				if (!checkBuff())
					if (!checkRecovery())
						if (!checkWeapon())
							if (!checkAttack())
								super.nextAction();
								/*if (!executeSkill(GeneralType.Defend)) {

									// Utility.narrate(character.name
									// + " decided to stay idle this turn");
		
								}*/
	}
	
	public boolean checkAttack(){
		if (character.getSp()/character.getMaxSP() < 0.3f)
			return false;
		
		Character target = character.getTarget();
		LinkedList<Skill> skills = character.getSkill(GeneralType.Attack);
		Collections.shuffle(skills);
		for (Skill skill: skills)
			if (skill.checkCondition() && (skill.checkCompatability(target)))
				return skill.execute();
		return false;
	}

	public boolean checkBarrier() {
		for (Skill skill : character.skills)
			if (skill.type == ActionType.Barrier) {
				boolean already = false;
				for (Barrier barrier : character.barriers)
					if (barrier.name.equalsIgnoreCase(skill.name))
						already = true;
				if (!already && skill.checkCondition())
					return skill.execute();
			}
		return false;
	}

	public boolean checkBuff() {
		for (Skill skill : character.skills)
			if (skill.type == ActionType.Buff) {
				boolean already = false;
				for (Buff buff : character.buffs)
					if (buff.name.equalsIgnoreCase(skill.name))
						already = true;
				if (!already && skill.checkCondition())
					return skill.execute();
			}
		return false;
	}

	public boolean checkWeapon() {
		Weapon weapon = character.weapon;
		if (weapon.isJammed) {
			return character.getSkill("Jam Fix").execute();
		} else if (weapon.useAmmo
				&& ((weapon.isAutomatic && weapon.ammo < weapon.fireRate) || weapon.ammo == 0))
			return character.getSkill("Reload").execute();
		return false;
	}

	public boolean checkRecovery() {
		if (rand.nextFloat() > chanceRecover)
			return false;

		if (character.getHp() / character.getMaxHP() < recoverLevel) {
			return executeSkill(ActionType.RecoverHP);
		}
		if (character.getSp() / character.getMaxSP() < recoverLevel) {
			return executeSkill(ActionType.RecoverSP);
		}
		if (character.getMp() / character.getMaxMP() < recoverLevel) {
			return executeSkill(ActionType.RecoverMP);
		}
		return false;
	}

	public boolean checkDefense() {
		Character charTarget = character.getTarget();
		if (charTarget == null)
			return false;

		Skill skill = charTarget.skillCurrent;
		if (skill == null || charTarget.getTarget() != character) {
			// System.out.println("not targeted" + character.getTarget() .name);
			return false;
		}

		boolean check = false;
		float damage = 0;
		Step step = null;
		Event event = null;

		// check if there are projectiles heading to character
		for (Event e : Game.getInstance().events)
			if (e.target == character || (e.step.isAOE && e.source.isAllied != character.isAllied)) {
				damage = e.step.parent.getTotalDamage() / character.getHp();
				check = true;
				event = e;
				step = event.step;

			}

		// check if target is attacking
		if (skill.type.getGeneralType() == GeneralType.Attack
				&& (skill.isLoading || skill.type == ActionType.RangeNormal || skill.type == ActionType.Magic)) {
			float damageS = skill.getTotalDamage() / character.getHp();
			System.out.println("dmg: " + skill.getTotalDamage());
			if ((check && damageS > damage) || !check) {
				step = skill.getCurrentStep();
				damage = damageS;
				check = true;
			}
		}

		if (check)
			if (rand.nextFloat() <= chanceDefend + damage) {
				boolean execute = false;

				ActionType type = null;
				if (step.getType() == Damage.MELEE)
					type = ActionType.DefendMelee;
				if (step.getType() == Damage.MAGIC)
					type = ActionType.DefendMagic;
				if (step.getType() == Damage.RANGE)
					type = ActionType.DefendRange;

				if (type != null)
					if (event == null) {
						if (executeSkill(type, null, charTarget))
							return true;
					} else if (executeSkill(type, null, event))
						return true;

				if (!execute)
					return executeSkill(null, GeneralType.Defend, charTarget);

			}
		return false;
	}

	public Skill getRandomSkill(LinkedList<Skill> skills) {
		if (skills.size() == 0)
			return null;
		if (skills.size() == 1)
			return skills.getFirst();
		else
			return skills.get(new Random().nextInt(skills.size() - 1));
	}

	public boolean executeSkill(ActionType type, GeneralType typeGen,
			Event event) {
		System.out.println("Defend Range!!");
		System.out.println("Defend Range!!");
		System.out.println("Defend Range!!");
		if (type == null)
			typeGen = GeneralType.Defend;

		LinkedList<Skill> applicable = new LinkedList<Skill>();
		LinkedList<Skill> tooFast = new LinkedList<Skill>();

		float tick = event.time, load = event.time;

		for (Skill skill : character.skills)
			if ((type != null && skill.type == type)
					|| (typeGen != null && skill.type.getGeneralType() == typeGen)) {
				Step s = skill.steps.getFirst();
				float sExecute = s.getExecutionTime() + s.getLoadTime() + s.getEventTime();
				if (s.getLoadTime() + s.getEventTime() < load && sExecute > load)
					applicable.add(skill);
				else if (s.getLoadTime() < load)
					tooFast.add(skill);
			}

		if (applicable.size() > 0) {
			Collections.shuffle(applicable);
			for (Skill skill : applicable)
				if (skill.checkCompatability(event.step.getCharacter())
						&& skill.checkCondition())
					return skill.execute();
		}
		if (tooFast.size() > 0) {
			Collections.shuffle(tooFast);
			for (Skill skill : tooFast) {
				float wait = load - skill.steps.getFirst().getLoadTime()
						- skill.steps.getFirst().getExecutionTime()  - skill.steps.getFirst().getEventTime();
				System.out.println("wait " + wait + " load " + load + " skill "
						+ skill.steps.getFirst().getLoadTime());
				skill = character.getSkill("Wait");
				if (skill.checkCompatability(event.step.getCharacter())
						&& skill.checkCondition())
					return skill.execute(character.getTarget(), wait);
			}
		}
		System.out.println("FAILED! " + load + " " + type + typeGen + " app "
				+ applicable.size() + " " + tooFast.size());
		return false;
	}

	public boolean executeSkill(ActionType type, GeneralType typeGen,
			Character target) {
		System.out.println("Defend Skill!!");
		System.out.println("Defend Skill!!");
		System.out.println("Defend Skill!!");
		LinkedList<Skill> applicable = new LinkedList<Skill>();
		LinkedList<Skill> tooFast = new LinkedList<Skill>();

		Step step = target.skillCurrent.getCurrentStep();
		float tick = (character.skillCurrent != null)? character.skillCurrent.tick: 0, load = tick + step.getEventTime(), execute = step
				.getExecutionTime() + tick;

		for (Skill skill : character.skills)
			if ((type != null && skill.type == type)
					|| (typeGen != null && skill.type.getGeneralType() == typeGen)) {
				Step s = skill.steps.getFirst();
				float sExecute = s.getExecutionTime() + s.getLoadTime() +s.getEventTime();
				if (s.getLoadTime() + s.getEventTime()< load && sExecute > load)
					applicable.add(skill);
				else if (s.getLoadTime() < load)
					tooFast.add(skill);
			}

		if (applicable.size() > 0) {
			Collections.shuffle(applicable);
			for (Skill skill : applicable)
				if (skill.checkCompatability(target)
						&& skill.checkCondition())
					return skill.execute();
		}
		if (tooFast.size() > 0) {
			Collections.shuffle(tooFast);
			for (Skill skill : tooFast) {
				float wait = load - skill.steps.getFirst().getLoadTime()
						- skill.steps.getFirst().getExecutionTime()  -  skill.steps.getFirst().getEventTime();
				System.out.println("wait " + wait + " load " + load + " skill "
						+ skill.steps.getFirst().getLoadTime());
				skill = character.getSkill("Wait");
				if (skill.checkCompatability(target)
						&& skill.checkCondition())
					return skill.execute(target, wait);
			}
		}
		System.out.println("FAILED! " + load + " " + type + typeGen);
		return false;
	}

	public boolean executeSkill(ActionType type) {
		int retry = 5;
		for (int i = 0; i < retry; i++) {
			Skill skill = getRandomSkill(type);
			if (skill != null && skill.checkCondition()) {
				skill.execute();
				return true;
			}
		}
		return false;
	}

	public boolean executeSkill(GeneralType type) {
		int retry = 5;
		for (int i = 0; i < retry; i++) {
			Skill skill = getRandomSkill(type);
			if (skill != null && skill.checkCondition()) {
				skill.execute();
				return true;
			}
		}
		return false;
	}
}
