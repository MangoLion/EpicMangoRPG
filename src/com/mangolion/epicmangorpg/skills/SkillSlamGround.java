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

public class SkillSlamGround extends Skill {
	Random rand = new Random();


	public SkillSlamGround() {
		super("Ground Slam", "Slam the sword into the ground, knocking enemies off balance, bonus 20% damage with great swords",Weapons.Bladed, ActionType.MeleeSwing);
		hasTarget = false;
		addSteps(new StepMeleeSlash(this, "Ground Slam","", 0.6f,
				0.6f, 0.3f, 2f) {
			public float getDamage() {
				if (getCharacter().weapon.checkType(Weapons.GreatSword))
					return super.getDamage()*1.2f;
				else
					return super.getDamage();
			};
		}.setAOE(true).setCost(40, 0, 15, 0)
		.setElement(new Element("Earth", 1)));
	}
	
}
