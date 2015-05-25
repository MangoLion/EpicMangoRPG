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

public class SkillRage extends Skill{

	public SkillRage() {
		super("Rage", "Gives a 100% increase in str and agi for 1 seconds, all skills used in this period will cost 50% more in everything, including time, dex reduced to 50%",Weapons.ALL, ActionType.None);
		hasTarget = false;
		addSteps(new StepRage(this, 0.1f, 0.5f, 0.0f).setCost(0, 0, 0, 0));
	}

	public static class StepRage extends Step{
		public float chanceParry = 0.8f;
		public StepRage(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Rage", "",ActionType.None, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name focuses intently on the enemy, predicting the next movement"), null, new MsgBasicCD(), null);
		}
		public float getSPBuff() {
			// TODO Auto-generated method stub
			return prof * 15;
		}
		
		@Override
		public void execute(Character target, float time) {
			//System.out.println("" + -getCharacter().str*0.7f);
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Focus", 1, GenType.positive, Buff.Type.str, Buff.Type.agi, Buff.Type.dex, Buff.Type.spCost, Buff.Type.mpCost, Buff.Type.balCost ).setValue((prof + 1), (prof + 1), (prof + 1)/2,0.5f*(1 - prof ),0.5f*(1 - prof ),0.5f*(1 - prof )));
			super.execute(target, time);
		}

	}
}
