package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepStab;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillStab extends Skill {

	public SkillStab() {
		super("Stab", "Leaves lots of openings, but used to bypass the block skill, can be easily(higher chance) parried and dodged.",Weapons.Bladed, ActionType.MeleeStab) ;
		isTwoHanded = false;
		addSteps(new StepStab(this, "Stab", "", 0.6f, 0.3f, 0.4f, 2f){
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
