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

public class SkillBodySlam extends Skill {
	Random rand = new Random();


	public SkillBodySlam() {
		super("Body Slam", "can stun and take target off balance, cannot be parried.",Weapons.ALL, ActionType.MeleeSpecial);
		addSteps(new StepMeleeKick(this, "Body Slam", "",0.8f,
				0.3f, 1f, 60, 3.5f) {
			@Override
			public void init() {
				super.init();
				cancelfromStun = false;
				chanceParry = 0;
				chanceBlock = 0;
				setStatus(new StatusStun(null, 1.1f), 1);
				setMessages(null, null, null, null);
				setObservable(true, 1f);
			}
			
			@Override
			public float getSPBuff() {
				// TODO Auto-generated method stub
				return prof*10;
			}
			
			@Override
			public float getBalBuff() {
				// TODO Auto-generated method stub
				return prof*20;
			}
		}.setChances(1, 0, 0.2f)
		.setCost(30, 0, 50, 0));
	}
	
}
