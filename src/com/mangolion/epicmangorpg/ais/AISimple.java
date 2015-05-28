package com.mangolion.epicmangorpg.ais;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Damage;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;

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
			Utility.narrate(character.name + " decided to stay idle this turn");
			character.ai = null;
			return;
		}
		if (!checkDefense())
			if (!checkBarrier())
				if (!checkBuff())
					if (!checkRecovery())
						if (!executeSkill(GeneralType.Attack))
							if (!executeSkill(GeneralType.Defend)) {

								Utility.narrate(character.name
										+ " decided to stay idle this turn");
								super.nextAction();
							}
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

		float tempChance = chanceDefend;

		if (skill.type.getGeneralType() == GeneralType.Attack
				&& (skill.isLoading || ((skill.getType() == Damage.MAGIC || skill
						.getType() == Damage.RANGE) && !skill.isCooldown)))
			if (rand.nextFloat() <= chanceDefend
					+ skill.getTotalDamage() / character.getHp()) {
				boolean execute = false;
				if (skill.getType() == Damage.MELEE)
					execute = executeSkill(ActionType.DefendMelee);
				if (skill.getType() == Damage.MAGIC)
					execute = executeSkill(ActionType.DefendMagic);
				if (skill.getType() == Damage.MELEE)
					execute = executeSkill(ActionType.DefendMelee);
				if (!execute)
					return executeSkill(GeneralType.Defend);
				return true;

			}
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
