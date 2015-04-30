package com.mangolion.epicmangorpg.steps;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgSkillInterrupt;
import com.mangolion.epicmangorpg.messages.MsgSkillInterruptFail;
import com.mangolion.epicmangorpg.messages.MsgSlashExecute;
import com.mangolion.epicmangorpg.messages.MsgSlashLoad;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillDodge.StepDodge;
import com.mangolion.epicmangorpg.skills.SkillParry.StepParry;
import com.mangolion.epicmangorpg.weapons.Weapons;

public abstract class StepMeleeSlash extends Step {



	public StepMeleeSlash(Skill parent, String name,  String desc, float timeLoad,
			float timeExecute, float timeCooldown, float dmgPercent) {
		super(parent, name, desc, ActionType.MeleeSwing, timeLoad, timeExecute,
				timeCooldown, dmgPercent);
		chanceBlock = 0.5f; // cancel both melee skills
		chanceParry = 0.5f;// both skills goes pass
							// each other and deal
							// damage,
		chanceMiss = 0f;
		setMessages(new MsgSlashLoad(), new MsgSlashExecute(), new MsgBasicCD());
	}
	
	@Override
	public void execute(Character target, float time) {
		if (!isAOE && !calculateChance(target)){
			damage(target);
			super.execute(target, time);
		}else  if (isAOE)
		{
			for (Character character: Game.getInstance().getEnemies(getCharacter()))
				calculateChance(character);
			damage(target);
			super.execute(target, time);
		}
	}

	public boolean calculateChance(Character target) {
		if (target == null)
			return false;
		


		Skill skill = target.skillCurrent;
		Step step = target.getCurrentStep();
		if (step == null)
			return false;
		if (skill.isLoading) {
			if (rand.nextInt((int) (100 - target.getBal() / target.maxBal / 2)) == 0) {
				StylePainter.append(new MsgSkillInterrupt().getMessage(
						getCharacter(), target, 0));
				step.cancel();
				return false;
			} else {
				StylePainter.append(new MsgSkillInterruptFail().getMessage(
						getCharacter(), target, 0));
			}
		}

		if (!isAOE && step.type == ActionType.Dodge && skill.isExecuting
				&& rand.nextFloat() <= (step.chanceDodge) /  getCharacter().weapon.sizeModifier) {
			StylePainter.append(msgMiss.getMessage(getCharacter(), target, 0));
			step.addProf(new Proficiency(target, getCharacter()));
			aoeExceptions.add(target);
			cancel();
			return true;
		}
		if (step.type == ActionType.MeleeParry
				&& skill.isExecuting
				&& rand.nextFloat() <= ((StepParry) step).chanceParry*chanceParry
						/ getCharacter().weapon.sizeModifier) {
			StylePainter.append(msgParry.getMessage(target, getCharacter(), 0));
			step.addProf(new Proficiency(target, getCharacter()));
			aoeExceptions.add(target);
			cancel();
			return true;
		}
		if (step.type == ActionType.MeleeBlock && skill.isExecuting && chanceBlock > 0) {
			StylePainter.append(new StyleSegment(StylePainter.NAME,
					parent.character.name), new StyleSegment(null, "'s "),
					new StyleSegment(StylePainter.SKILL, name),
					new StyleSegment(StylePainter.BOLD, " is blocked by "),
					new StyleSegment(StylePainter.NAME, target.name),
					new StyleSegment(null, "'s "), new StyleSegment(
							StylePainter.SKILL, step.name, true));
			float finalDamage = getDamage() - step.value;
			if (finalDamage <= 0)
				finalDamage = 1;
			target.setDamage(parent.character, finalDamage);
			System.out.println("blocked");
			aoeExceptions.add(target);
			step.addProf(new Proficiency(target, getCharacter()));
			return true;
		}
		if ((step.type == ActionType.MeleeSwing || step.type == ActionType.MeleeStab)
				&& skill.isExecuting) {
			// Utility.narrate("Chance of Interaction!!!");
			if (rand.nextFloat() <= chanceBlock /  getCharacter().weapon.sizeModifier) {
				StylePainter.append(new StyleSegment(StylePainter.NAME,
						parent.character.name), new StyleSegment(null, "'s "),
						new StyleSegment(StylePainter.SKILL, name),
						new StyleSegment(StylePainter.BOLD, " is blocked by "),
						new StyleSegment(StylePainter.NAME, target.name),
						new StyleSegment(null, "'s "), new StyleSegment(
								StylePainter.SKILL, step.name, true));
				damage(target, step.value);
				return true;
			} else if ((step.parent.isLoading || step.parent.isCooldown)
					&& rand.nextFloat() <= chanceParry /  getCharacter().weapon.sizeModifier) {
				/*
				 * Utility.narrate(parent.character.name + "'s " +
				 * parent.character.getCurrentStep().name + " is parried by " +
				 * target.name + "'s " + step.name);
				 */
				StylePainter.append(new StyleSegment(StylePainter.NAME,
						parent.character.name), new StyleSegment(null, "'s "),
						new StyleSegment(StylePainter.SKILL, name),
						new StyleSegment(StylePainter.BOLD, " is parried by "),
						new StyleSegment(StylePainter.NAME, target.name),
						new StyleSegment(null, "'s "), new StyleSegment(
								StylePainter.SKILL, step.name, true));
				step.addProf(new Proficiency(target, getCharacter()));
				aoeExceptions.add(target);
				step.cancel();
				cancel();
				return true;
			}
		}

		return false;
	}

}
