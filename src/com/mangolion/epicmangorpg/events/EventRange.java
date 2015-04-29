package com.mangolion.epicmangorpg.events;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgSkillInterrupt;
import com.mangolion.epicmangorpg.messages.MsgSkillInterruptFail;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillParry.StepParry;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.steps.Step;

public class EventRange extends Event{
	public float chanceMiss = 0.2f, chanceDodge  = 1, chanceParry = 1, chanceBlock =1;
	public Msg msgLoad, msgCooldown, msgMiss = new MsgSlashMiss(), msgParry;
	public float dmgBase, chanceStatus = 0;
	public Status status;
	public boolean isAOE = false;

	
	public EventRange(String name, String desc, float time, Character souce,
			Character target, float dmgbase, Step step) {
		super(name, desc, time, souce, target, null, step);
		this.dmgBase = dmgbase;
	}
	
	public EventRange(String name, String desc, float time, Character souce,
			Character target, float dmgbase, Step step, boolean aoe) {
		super(name, desc, time, souce, target, null, step);
		this.dmgBase = dmgbase;
		isAOE = aoe;
	}
	
	@Override
	public void execute() {
		if (!isAOE && !calculateChance(target)){
			step.damage(target);
			super.execute();
		}else  if (isAOE)
		{
			for (Character character: Game.getInstance().getEnemies(step.getCharacter())){
				if (!calculateChance(character))
					step.damage(character);
			}
			super.execute();
		}
	}
	
	public float getDamage(){
		return dmgBase;
	}
	
	public Event setChances(float block, float parry, float miss, float dodge) {
		chanceBlock = block;
		chanceParry = parry;
		chanceMiss = miss;
		chanceDodge = dodge;
		return this;
	}
	
	public Event setMessages(Msg load, Msg execute, Msg cooldown, Msg parry) {
		msgLoad = load;
		msgExecute = execute;
		msgCooldown = cooldown;
		msgParry = parry;
		return this;
	}
	
	public Event setStatus(Status status, float chance){
		this.status = status;
		chanceStatus = chance;
		return this;
	}
	
	public void damage(Character target, float subtract){
		float dmg = getDamage() - subtract;
		if (dmg <= 0)
			dmg = 1;
			target.setDamage(source, dmg);
		if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
			target.addStatus(getStatus());
	}
	
	private Status getStatus() {
		Status newStatus =status.copy();
		newStatus.character = source.getTarget();
		return newStatus;
	}

	public void damage(Character target){
		float dmg = getDamage();
			target.setDamage(source, dmg);
			if (chanceStatus > 0 && rand.nextFloat() <= chanceStatus)
				target.addStatus(getStatus());
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
						source, target, 0));
				return false;
			} else {
				StylePainter.append(new MsgSkillInterruptFail().getMessage(
						source, target, 0));
			}
		}

		if (step.type == ActionType.Dodge && skill.isExecuting
				&& rand.nextFloat() <= (step.chanceDodge) /  source.weapon.sizeModifier) {
			StylePainter.append(msgMiss.getMessage(source, target, 0));
			;
			return true;
		}
		if (step.type == ActionType.MeleeParry
				&& skill.isExecuting
				&& rand.nextFloat() <= ((StepParry) step).chanceParry
						/ source.weapon.sizeModifier) {
			StylePainter.append(msgParry.getMessage(target, source, 0));
			;
			return true;
		}

		if (step.type == ActionType.MeleeBlock && skill.isExecuting && chanceBlock > 0) {
			StylePainter.append(new StyleSegment(StylePainter.NAME,
					source.name), new StyleSegment(null, "'s "),
					new StyleSegment(StylePainter.SKILL, name),
					new StyleSegment(StylePainter.BOLD, " is blocked by "),
					new StyleSegment(StylePainter.NAME, target.name),
					new StyleSegment(null, "'s "), new StyleSegment(
							StylePainter.SKILL, step.name, true));
			float finalDamage = getDamage() - step.value;
			if (finalDamage <= 0)
				finalDamage = 1;
			target.setDamage(source, finalDamage);
			return true;
		}
		if ((step.type == ActionType.MeleeSwing || step.type == ActionType.MeleeStab)
				&& skill.isExecuting) {
			// Utility.narrate("Chance of Interaction!!!");
			if (rand.nextFloat() <= chanceBlock /  source.weapon.sizeModifier) {
				StylePainter.append(new StyleSegment(StylePainter.NAME,
						source.name), new StyleSegment(null, "'s "),
						new StyleSegment(StylePainter.SKILL, name),
						new StyleSegment(StylePainter.BOLD, " is blocked by "),
						new StyleSegment(StylePainter.NAME, target.name),
						new StyleSegment(null, "'s "), new StyleSegment(
								StylePainter.SKILL, step.name, true));
				damage(target, step.value);
				return true;
			} else if ((step.parent.isLoading || step.parent.isCooldown)
					&& rand.nextFloat() <= chanceParry /  source.weapon.sizeModifier) {
				/*
				 * Utility.narrate(source.name + "'s " +
				 * source.getCurrentStep().name + " is parried by " +
				 * target.name + "'s " + step.name);
				 */
				StylePainter.append(new StyleSegment(StylePainter.NAME,
						source.name), new StyleSegment(null, "'s "),
						new StyleSegment(StylePainter.SKILL, name),
						new StyleSegment(StylePainter.BOLD, " is parried by "),
						new StyleSegment(StylePainter.NAME, target.name),
						new StyleSegment(null, "'s "), new StyleSegment(
								StylePainter.SKILL, step.name, true));
				return true;
			}
		}

		if (rand.nextFloat() <= chanceMiss) {
			StylePainter.append(new MsgSlashMiss().getMessage(source,
					target, 0));
			return true;
		}
		return false;
	}

}
