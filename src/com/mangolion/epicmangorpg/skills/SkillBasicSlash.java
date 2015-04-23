package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBasicSlash extends Skill {
	Random rand = new Random();


	public SkillBasicSlash() {
		super("Slash", "Basic  slash, fast, but weak.Very fast loading speed, but slightly longer cooldown",Weapons.Sword, ActionType.MeleeSwing);
		addSteps(new StepMeleeSlash(this, "Slash","", 0.5f,
				0.2f, 0.4f, 0, 0, 10, 5,1f) {
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
		});
		weapons.add(Weapons.Dagger);
		weapons.add(Weapons.GreatSword);
	}
	
}
