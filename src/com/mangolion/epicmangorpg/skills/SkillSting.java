package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepStab;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSting extends Skill {

	public SkillSting() {
		super("Sting", "The stab version of stuff that has stingers, will poison.",Weapons.ALL, ActionType.MeleeStab) ;
		addSteps(new StepStab(this, "Sting", "", 0.6f, 0.3f, 0.4f,2f){
			
		}.setStatus(new StatusPoison(null, 1), 0.8f).setCost(25, 0, 10, 0));
	}
	
}
