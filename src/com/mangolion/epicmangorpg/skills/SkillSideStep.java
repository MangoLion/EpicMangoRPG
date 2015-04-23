package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSideStep extends Skill{

	public SkillSideStep() {
		super("Side Step", "Side stepping to dodge most melee skills, cannot dodge AOE skills. Its fast but very short.",Weapons.ALL, ActionType.Dodge);
		addSteps(new StepDodge("Sidestep", this, 0.2f, 0.3f, 0.2f, 0, 0, 10, 20));
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
