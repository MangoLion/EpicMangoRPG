package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBasicSwordCombo extends Skill {
	Random rand = new Random();


	public SkillBasicSwordCombo() {
		super("Basic Sword Combo", "If successful, this combo deals much more damage than a normal slash, but it can easily interupted..",Weapons.Sword, ActionType.MeleeSwing);
		addSteps(new StepMeleeSlash(this, "First Slash", "Weak, like a normal slash but shorter cooldown",0.5f,
				0.3f, 0.4f, 0, 0, 10, 5,1) {
			public float getStrBuff() {return prof * 10;};
		}.setStatus(new StatusStun(null, 0.4f), 0.8f),
				new StepMeleeSlash(this, "Second Slash", "Stronger than a slash and execute longer", 0.5f, 0.5f,
				0.5f, 0, 0, 7, 6,1.6f) {
			public float getStrBuff() {return prof * 12;};
		}.setStatus(new StatusStun(null, 0.6f), 1.2f),
				new StepMeleeSlash(this, "Final Slash", "Longest, most powerful slash.",0.6f, 0.6f, 0.6f,
				0, 0, 15, 9,2.2f) {
			public float getStrBuff() {return prof * 15;};
		}.setStatus(new StatusStun(null, 0.9f), 2f));
	}
	
}
