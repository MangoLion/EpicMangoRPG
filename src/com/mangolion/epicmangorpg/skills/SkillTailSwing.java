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

public class SkillTailSwing extends Skill {
	Random rand = new Random();


	public SkillTailSwing() {
		super("Tail Swing", "",Weapons.ALL, ActionType.MeleeSwing);
		hasTarget = false;
		addSteps(new StepMeleeSlash(this, "Tail Swing","", 0.8f,
				0.4f, 0.4f,1f) {
			
		}.setAOE(true).setCost(30, 0, 20, 0));
	}
	
}
