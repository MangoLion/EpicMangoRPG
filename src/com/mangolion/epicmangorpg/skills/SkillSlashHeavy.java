package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSlashHeavy extends Skill {
	Random rand = new Random();


	public SkillSlashHeavy() {
		super("Heavy Slash", "A deadly and crushing slash, will stun, but leaves alot of openings.",Weapons.GreatSword, ActionType.MeleeSwing);
		shopPrice = 20;
		addSteps(new StepSlashHeavy(this, "Heavy Slash","",1f,
				0.8f, 0.7f, 3) {}.setCost(25, 0, 12, 0));
		setObservable(true, 0.8f);
	}
	
	public static class StepSlashHeavy extends StepMeleeSlash{

		public StepSlashHeavy(Skill parent, String name, String desc, float timeLoad,
				float timeExecute, float timeCooldown,float baseDamage_) {
			super(parent, name, desc, timeLoad, timeExecute, timeCooldown, baseDamage_);
			cancelfromStun = false;
			setStatus(new StatusStun(null, 0.9f), 1);
			
		}
		public float getStrBuff() {
			// TODO Auto-generated method stub
			return prof * 25;
		}
		public float getBalBuff() {
			return prof*15;
		};
	}
}
