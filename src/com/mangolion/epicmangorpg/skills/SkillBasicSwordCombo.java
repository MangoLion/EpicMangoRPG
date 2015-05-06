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
		super("Basic Sword Combo", "If successful, this combo deals much more damage than a normal slash, but it can easily interupted..",Weapons.Bladed, ActionType.MeleeSwing);
		setObservable(true, 0.7f);
		addSteps(new StepMeleeSlash(this, "First Slash", "Weak, like a normal slash but shorter cooldown",0.5f,
				0.2f, 0.2f,1) {
			public float getStrBuff() {return prof * 10;};
		}.setStatus(new StatusStun(null, 0.4f), 0.8f).setCost(10, 0, 5, 5),
				new StepMeleeSlash(this, "Second Slash", "Stronger than a slash and execute longer", 0.3f, 0.2f,
				0.3f,1.6f) {
			public float getStrBuff() {return prof * 12;};
		}.setStatus(new StatusStun(null, 0.6f), 1.2f).setCost(15, 0, 5, 5),
				new StepMeleeSlash(this, "Final Slash", "Longest, most powerful slash.",0.4f, 0.6f, 0.6f,2.2f) {
			public float getStrBuff() {return prof * 15;};
		}.setStatus(new StatusStun(null, 1f), 2f).setCost(20, 0, 5, 5));
	}
	
}
