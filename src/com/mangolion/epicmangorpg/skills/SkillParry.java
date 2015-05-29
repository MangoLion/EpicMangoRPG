package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.messages.MsgParryLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillParry extends Skill{

	public SkillParry() {
		super("Parry", "Very high chance of success compared to dodge, used to deflect most melee attacks and even some ranged attack, if succeed, the user will receive no damage and also can take advantage of the target's opening.",Weapons.ALL, ActionType.MeleeParry);
		addSteps(new StepParry(this, 0.3f, 0.4f, 0.2f).setCost(10, 0, 0, 0));
		isTwoHanded = false;
		customTime = true;
		hasTarget = false;
	}

	public static class StepParry extends Step{
		public float chanceParry = 0.8f;
		public StepParry(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Parry", "",ActionType.MeleeParry, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new MsgParryLoad(), null, new MsgBasicCD(), null);
			chanceParry= 1;
		}
		public float getStrBuff() {
			// TODO Auto-generated method stub
			return prof * 10;
		}
		@Override
		public float getBalBuff() {
			// TODO Auto-generated method stub
			return prof*20;
		}
		@Override
		public float getDexBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
		@Override
		public float getSPBuff() {
			
			return Utility.format4(prof*10f);
		}
		@Override
		public boolean isCustomTime() {
			// TODO Auto-generated method stub
			return true;
		}
	}
	
	@Override
	public boolean checkCompatability(Character target) {
		Skill skill = target.skillCurrent;
		if (skill == null)
			return true;
		Step step = skill.steps.getFirst();
		return step.chanceParry > 0;
	}
}
