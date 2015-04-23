package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeKick;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBasicKick extends Skill {
	Random rand = new Random();

	public SkillBasicKick() {
		super(
				"Basic Kick",
				"This skill cannot be used to counter other melee atks due to its long load time, but it can stun and takes away the target's balance.",
				Weapons.ALL, ActionType.MeleeSwing);
		addSteps(new StepMeleeKick(this, "Basic Kick", "", 0.7f, 0.1f, 0.5f, 0,
				0, 10, 10, 15, 2f) {
			@Override
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}

			@Override
			public float getBalBuff() {
				// TODO Auto-generated method stub
				return prof * 20;
			}
		}.setStatus(new StatusStun(null, 0.7f), 0.8f));
	}

}
