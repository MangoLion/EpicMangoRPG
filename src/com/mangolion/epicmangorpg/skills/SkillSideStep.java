package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepDodge;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSideStep extends Skill{

	public SkillSideStep() {
		super("Side Step", "Side stepping to dodge most melee skills, cannot dodge AOE skills. Its fast but very short.",Weapons.ALL, ActionType.Dodge);
		addSteps(new StepDodge("Sidestep", this, 0.2f, 0.3f, 0.2f){
			
			@Override
			public float getAgiBuff() {
				// TODO Auto-generated method stub
				return prof*20;
			}
			
			@Override
			public float getSPBuff() {
				// TODO Auto-generated method stub
				return prof*10;
			}
		}.setCost(15, 0, 8, 0));
	}
}
