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

public class SkillSlashSpin extends Skill {
	Random rand = new Random();


	public SkillSlashSpin() {
		super("Spinning Slash", "Spins the sword in a circle, damaging all enemies",Weapons.Bladed, ActionType.MeleeSwing);
		hasTarget = false;
		addSteps(new StepMeleeSlash(this, "Spinning Slash","", 0.8f,
				0.6f, 0.3f, 2f) {
		}.setAOE(true).setCost(40, 0, 15, 0));
		setObservable(true, 1);
	}
	
}
