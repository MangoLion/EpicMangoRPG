package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.messages.MsgParryLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillParry extends Skill{

	public SkillParry() {
		super("Parry", "Very high chance of success compared to dodge, used to deflect most melee attacks and even some ranged attack, if succeed, the user will receive no damage and also can take advantage of the target's opening.",Weapons.ALL, ActionType.MeleeParry);
		addSteps(new StepParry(this, 0.3f, 0.5f, 0.2f, 0, 0, 5, 15));
	}

	public static class StepParry extends Step{
		public float chanceParry = 0.8f;
		public StepParry(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown,
				float hpCost, float mpCost, float balCost, float stamCost) {
			super(parent, "Parry", "",ActionType.MeleeParry, timeLoad, timeExecute, timeCooldown, hpCost, mpCost,
					balCost, stamCost, 0);
			setMessages(new MsgParryLoad(), null, new MsgBasicCD());
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
		public boolean isCustomTime() {
			// TODO Auto-generated method stub
			return true;
		}
	}
}
