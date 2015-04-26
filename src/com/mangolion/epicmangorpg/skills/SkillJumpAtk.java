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

public class SkillJumpAtk extends Skill {
	Random rand = new Random();


	public SkillJumpAtk() {
		super("Jump Attack", "Like a kick, it can stun and knock off balance, however, the user's balance is lost as well. Cannot be parried.",Weapons.ALL, ActionType.MeleeSpecial);
		addSteps(new StepMeleeKick(this, "Jump Attack", "",0.7f,
				0.3f, 1f, 60, 3.5f) {
			@Override
			public void init() {
				super.init();
				cancelfromStun = false;
				setStatus(new StatusStun(null, 0.6f), 1);
				setMessages(null, null, null);
				setObservable(true, 1f);
			}
			
			@Override
			public float getStrBuff() {
				
				return Utility.format4(prof*10f);
			}
			
			@Override
			public float getBalBuff() {
				
				return Utility.format4(prof*20f);
			}
		}.setChances(1, 0, 0.2f)
		.setCost(25, 0, 70, 0));
	}
	
}
