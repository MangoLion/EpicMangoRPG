package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillStomp extends Skill {
	Random rand = new Random();


	public SkillStomp() {
		super("Stomp", "Slam weapon on the ground with great force, knocking enemies off balance.",Weapons.ALL, ActionType.MeleeSwing);
		hasTarget = false;
		addSteps(new StepMeleeSlash(this, "Stomp","", 0.6f,
				0.3f, 0.2f, 1f) {
		}.setAOE(true).setCost(30, 0, 15, 0).setChances(1, 0, -1),
		new StepMeleeSlash(this, "Stomp","", 0.4f,
				0.3f, 0.6f, 1f) {
			
		}.setAOE(true).setCost(15, 0, 15, 0).setChances(1, 0, -1));
	}
	
}
