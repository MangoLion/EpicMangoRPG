package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.messages.MsgParryLoad;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillFireCoating extends Skill{

	public SkillFireCoating() {
		super("Fire Coat", "Coat your body and weapon in fire, you will deal more fire damage, also receive more damages from opposing elements. Last for 10 seconds",Weapons.ALL, ActionType.Magic);
		addSteps(new StepEle(this, 0.3f, 0.1f, 0.1f).setCost(0, 20, 0, 0));
		hasTarget = false;
	}

	public static class StepEle extends Step{
		public float chanceParry = 0.8f;
		public StepEle(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Fire Coat", "",ActionType.Magic, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name gathers elemental energy and prepares to project them onto $p3 body"), null, new MsgBasicCD());
			chanceParry= 1;
		}
		public float getIntBuff() {
			// TODO Auto-generated method stub
			return prof * 10;
		}
		
		@Override
		public void execute(Character target, float time) {
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new BuffEleFire(10));
			super.execute(target, time);
		}

	}
}
