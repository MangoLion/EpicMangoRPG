package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillShiftImpulse extends Skill{

	public SkillShiftImpulse() {
		super("Shift Impulse", "Gives a 50% increase in agi for 10 seconds, all skills used in this period will cost 20% more in everything, including time",Weapons.ALL, ActionType.Buff);
		hasTarget = false;
		addSteps(new StepFocus(this, 0.1f, 0.3f, 0.0f).setCost(0, 0, 0, 0));
	}

	public static class StepFocus extends Step{
		public float chanceParry = 0.8f;
		public StepFocus(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Shift Impulse", "",ActionType.Buff, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name relaxes and focuses keenly on $p3 senses"), null, new MsgBasicCD());
		}
		public float getSPBuff() {
			// TODO Auto-generated method stub
			return prof * 15;
		}
		
		@Override
		public void execute(Character target, float time) {
			//System.out.println("" + -getCharacter().str*0.7f);
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Shift Impulse", 10, GenType.positive, Buff.Type.agi, Buff.Type.spCost, Buff.Type.mpCost, Buff.Type.balCost ).setValue( (prof + 1)*0.5f,0.2f*(1 - prof ),0.2f*(1 - prof ),0.2f*(1 - prof )));
			super.execute(target, time);
		}

	}
}
