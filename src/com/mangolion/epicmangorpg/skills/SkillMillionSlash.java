package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillMillionSlash extends Skill {
	Random rand = new Random();

	public SkillMillionSlash() {
		super("Million Slash", "Despite the cool sounding name, it only executes 6 times, has a very small chance of stunning for each slash.",Weapons.Bladed, ActionType.MeleeStab);
		shopPrice = 85;
		setObservable(true, 0.7f);
		addSteps(new StepMeleeSlash(this, "First Slash","", 0.2f, 0.1f, 0.4f,0.7f) {
			@Override
			public boolean isCancelfromStun() {
				// TODO Auto-generated method stub
				return false;
			}
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 8;
			}
			
			public float getAgiBuff() {
				return prof*5;
			};
		}.setStatus(new StatusStun(null, 0.2f), 1f).setCost(12, 0, 5, 0), 
		new StepMeleeSlash(this, "Second Slash","", 0.2f, 0.1f, 0.2f,0.9f) {
			@Override
			public boolean isCancelfromStun() {
				// TODO Auto-generated method stub
				return false;
			}
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 9;
			}
			public float getAgiBuff() {
				return prof*6;
			};
		}.setStatus(new StatusStun(null, 0.2f), 1f).setCost(12, 0, 5, 0), 
		new StepMeleeSlash(this, "Third Slash", "",0.2f, 0.1f, 0.2f,1f) {
			@Override
			public boolean isCancelfromStun() {
				// TODO Auto-generated method stub
				return false;
			}
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
			public float getAgiBuff() {
				return prof*7;
			};
		}.setStatus(new StatusStun(null, 0.2f), 1f).setCost(16, 0, 8, 0), 
		new StepMeleeSlash(this, "Fourth Slash", "",0.1f, 0.1f, 0.3f, 1.2f) {
			@Override
			public boolean isCancelfromStun() {
				// TODO Auto-generated method stub
				return false;
			}
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 11;
			}
			public float getAgiBuff() {
				return prof*8;
			};
		}.setStatus(new StatusStun(null, 0.2f), 1f).setCost(18, 0, 10, 0), 
		new StepMeleeSlash(this, "Fifth Slash","", 0.1f, 0.1f, 0.2f,1.4f) {
			@Override
			public boolean isCancelfromStun() {
				// TODO Auto-generated method stub
				return false;
			}
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 12;
			}
			public float getAgiBuff() {
				return prof*8;
			};
		}.setStatus(new StatusStun(null, 0.2f), 1f).setCost(20, 0, 8, 0),  
		new StepMeleeSlash(this, "Final Slash", "",0.1f, 0.1f, 1.0f, 2f) {
			@Override
			public boolean isCancelfromStun() {
				// TODO Auto-generated method stub
				return false;
			}
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getAgiBuff() {
				return prof*10;
			};
		}.setStatus(new StatusStun(null, 1f), 1f).setCost(25, 0, 8, 0));
	}

}
