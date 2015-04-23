package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBarrelRoll extends Skill{

	public SkillBarrelRoll() {
		super("Barrel Roll", "Barrelrollling to dodge long barrage of range attacks, works on melee attacks too, cannot dodge AOE skills. While it has a long execution time, the user looses alot of balance and it takes a longer cooldown.",Weapons.ALL, ActionType.Dodge);
		addSteps(new StepDodge("Barrel roll", this, 0.5f, 1.2f, 0.5f, 0, 0, 60, 30));
		setObservable(true, 1);
	}

	public static class StepDodge extends Step{
		public float value = 0.5f;
		public StepDodge(String name, Skill parent,
				float timeLoad, float timeExecute, float timeCooldown,
				float hpCost, float mpCost, float balCost, float stamCost) {
			super(parent,name,"", ActionType.Dodge, timeLoad, timeExecute, timeCooldown, hpCost, mpCost,
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
