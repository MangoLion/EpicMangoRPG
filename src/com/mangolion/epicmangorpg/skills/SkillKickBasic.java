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

public class SkillKickBasic extends Skill {
	Random rand = new Random();

	public SkillKickBasic() {
		super(
				"Basic Kick",
				"This skill cannot be used to counter other melee atks due to its long load time, but it can stun and takes away the target's balance.",
				Weapons.ALL, ActionType.MeleeSwing);
		addSteps(new StepMeleeKick(this, "Basic Kick", "", 0.7f, 0.1f, 0.7f, 15, 2f) {
			@Override
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}

			@Override
			public float getSPBuff() {
				
				return Utility.format4(prof*10f);
			}
			
			@Override
			public float getBalBuff() {
				// TODO Auto-generated method stub
				return prof * 20;
			}
		}.setStatus(new StatusStun(null, 0.6f), 0.8f)
		.setCost(25, 0, 40, 0));
	}

}
