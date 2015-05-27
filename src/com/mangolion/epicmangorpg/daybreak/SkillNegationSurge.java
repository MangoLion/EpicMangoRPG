package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillNegationSurge extends Skill{

	public SkillNegationSurge() {
		super("Negation Surge", "Enable 50% of the user's melee damage to bypass barriers for 5 seconds",Weapons.ALL, ActionType.Buff);
		addSteps(new StepEle(this, 0.1f, 0.2f, 0f).setCost(0,10, 0, 0));
		hasTarget = false;
	}

	public static class StepEle extends Step{
		public float chanceParry = 0.8f;
		public StepEle(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Negation Surge", "",ActionType.Buff, timeLoad, timeExecute, timeCooldown, 0);
		}
		public float getIntBuff() {
			// TODO Auto-generated method stub
			return prof * 10;
		}
		
		@Override
		public void execute(Character target, float time) {
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Negation Surge", 5, Buff.GenType.positive, Buff.Type.barrierNegate).setValue(0.5f));
			super.execute(target, time);
		}

	}
}
