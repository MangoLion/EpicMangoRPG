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

public class SkillClawSnap extends Skill {
	Random rand = new Random();


	public SkillClawSnap() {
		super("Snap Claws", "Snapping Claws at target, intend to tear them appart.",Weapons.ALL, ActionType.MeleeSwing);
		addSteps(new StepMeleeSlash(this, "Snap Claws","", 0.7f,
				0.2f, 0.2f,1f) {}.setCost(15, 0, 0, 0),
				new StepMeleeSlash(this, "Snap Claws","", 0.2f,
						0.2f, 0.7f,1f) {}.setCost(10, 0, 0, 0));
	}
	
}
