package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSideJump extends Skill{

	public SkillSideJump() {
		super("Side Jump", "Jumping to the side to dodge, cannot dodge AOE skills. Used as a last ditch effort since its very fast, but the user will loose all balance and has a long cooldown",Weapons.ALL, ActionType.Dodge);
		addSteps(new StepDodge("Side Jump", this, 0.1f, 0.6f, 0.6f, 0, 0, 10000, 20));
	}

	public static class StepDodge extends Step{
		public float value = 0.5f;
		public StepDodge(String name, Skill parent,
				float timeLoad, float timeExecute, float timeCooldown,
				float hpCost, float mpCost, float balCost, float stamCost) {
			super(parent, name,"", ActionType.Dodge, timeLoad, timeExecute, timeCooldown, hpCost, mpCost,
					balCost, stamCost, 0);
			setMessages(new MsgDodgeLoad(), new MsgDodgeExecute(), new MsgBasicCD());
		}
		
		@Override
		public float getAgiBuff() {
			// TODO Auto-generated method stub
			return prof*20;
		}
		
	}
}
