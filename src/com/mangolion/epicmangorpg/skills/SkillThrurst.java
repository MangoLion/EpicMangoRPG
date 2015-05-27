package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepStab;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillThrurst extends Skill {

	public SkillThrurst() {
		super("Thurst", "The stab version for rapiers, faster, deadlier.",Weapons.Rapier, ActionType.MeleeStab) ;
		isTwoHanded = false;
		addSteps(new StepStab(this, "Thurst", "", 0.4f, 0.3f, 0.3f, 2f){
			public void init() {
				critBase = 0.2f * (1 + prof/2);
			};
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getAgiBuff() {
				return prof*5;
			};
		}.setCost(25, 0, 10, 0));
	}
	
}
