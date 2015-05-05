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

public class SkillBowSweep extends Skill {
	Random rand = new Random();


	public SkillBowSweep() {
		super("Bow Sweep", "Use the bow and sweep the target over its feet, taking away its balance",Weapons.Bow, ActionType.MeleeSwing);
		shopPrice = 10;
		addSteps(new StepMeleeSlash(this, "Bow Sweep","", 0.4f,
				0.2f, 0.2f,0.7f) {
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
			

		}.setCost(20, 0, 3, 0));
	}
	
}
