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

public class SkillMetabolicBoost extends Skill{

	public SkillMetabolicBoost() {
		super("Metabolic Boost", "Gives a 25% increase in stamina regeneration for 10 seconds, all skills used in this period will cost 5% more in everything, including time",Weapons.ALL, ActionType.Buff);
		hasTarget = false;
		addSteps(new StepFocus(this, 0.1f, 0.3f, 0.0f).setCost(0, 0, 0, 0));
	}

	public static class StepFocus extends Step{
		public float chanceParry = 0.8f;
		public StepFocus(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Metabolic Boost", "",ActionType.Buff, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name feels a rush of strength as $p3 body metabolism increases"), null, new MsgBasicCD());
		}
		public float getSPBuff() {
			// TODO Auto-generated method stub
			return prof * 15;
		}
		
		@Override
		public void execute(Character target, float time) {
			//System.out.println("" + -getCharacter().str*0.7f);
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Metabolic Boost", 10, GenType.positive, Buff.Type.spRegen, Buff.Type.spCost, Buff.Type.mpCost, Buff.Type.balCost ).setValue( (prof + 1)*0.25f,0.05f*(1 - prof ),0.05f*(1 - prof ),0.05f*(1 - prof )));
			super.execute(target, time);
		}

	}
}
