package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillDodge extends Skill{

	public SkillDodge() {
		super("Dodge", "Leaning back/stepping to the side to dodge most single target skills, cannot dodge AOE skills. While fast to execute, it has low success rate, so only use it as the last resort.",Weapons.ALL, ActionType.Dodge);
		addSteps(new StepDodge(this, 0.2f, 0.4f, 0.3f).setCost(25, 0, 15, 0));
		hasTarget = false;
	}

	public static class StepDodge extends Step{
		public float value = 0.5f;
		public StepDodge(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Dodge","", ActionType.Dodge, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new MsgDodgeLoad(), new MsgDodgeExecute(), new MsgBasicCD());
		}
		
		@Override
		public float getAgiBuff() {
			// TODO Auto-generated method stub
			return prof*20;
		}
		
	}
}
