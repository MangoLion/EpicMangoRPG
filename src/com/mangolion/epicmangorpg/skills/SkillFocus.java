package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.messages.MsgParryLoad;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillFocus extends Skill{

	public SkillFocus() {
		super("Focus", "Gives a 100% increase in dex for 1 seconds, all skills used in this period will cost 50% more in everything, including time",Weapons.ALL, ActionType.None);
		hasTarget = false;
		addSteps(new StepFocus(this, 0.1f, 0.3f, 0.0f).setCost(0, 0, 0, 0));
	}

	public static class StepFocus extends Step{
		public float chanceParry = 0.8f;
		public StepFocus(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Focus", "",ActionType.None, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name focuses intently on the enemy, predicting the next movement"), null, new MsgBasicCD());
		}
		public float getSPBuff() {
			// TODO Auto-generated method stub
			return prof * 15;
		}
		
		@Override
		public void execute(Character target, float time) {
			//System.out.println("" + -getCharacter().str*0.7f);
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Focus", 3, GenType.positive, Buff.Type.dex, Buff.Type.spCost, Buff.Type.mpCost, Buff.Type.balCost ).setValue( (prof + 1),0.5f*(1 - prof ),0.5f*(1 - prof ),0.5f*(1 - prof )));
			super.execute(target, time);
		}

	}
}
