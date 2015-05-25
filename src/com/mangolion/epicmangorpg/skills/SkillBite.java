package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBite extends Skill {
	Random rand = new Random();


	public SkillBite() {
		super("Bite", "Used by monsters with sharp teeth, beware of those with poison.",Weapons.ALL, ActionType.MeleeStab);
		addSteps(new StepMeleeSlash(this, "Bite","", 0.5f,
				0.3f, 0.3f,1.3f) {
			
			
		}.setChances(0.9f, 0, 0.1f)
		.setCost(20, 0, 10, 0)
		.setMessages(new Msg("$name lunges at $targetname, showing $p3 sharp teeth."), null, null, new Msg("Blood is seen everywhere as $name's teeth sunk into $targetname's body.")));
	}
	
}
