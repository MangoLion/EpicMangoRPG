package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillWait extends Skill{

	public SkillWait() {
		super("Wait", "Take no action for x seconds",Weapons.ALL, ActionType.None);
		hasTarget = false;
		customTime = true;
		addSteps(new Step(this, "wait","", ActionType.None, 0, -1, 0,  0){
			@Override
			public boolean isCustomTime() {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}

}
