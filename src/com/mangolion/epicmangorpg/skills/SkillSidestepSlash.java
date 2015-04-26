package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSidestepSlash extends Skill {
	Random rand = new Random();


	public SkillSidestepSlash() {
		super("Sidestep Slash", "Allows for a sidestep and attack at the same time.",Weapons.Dagger, ActionType.Dodge);
		addSteps(new StepSlashDodge(this, "Sidestep Slash", "",0.3f,
				0.4f, 0.2f, 0.6f) {

		}.setCost(20, 0, 8, 0));
		weapons.add(Weapons.Sword);
		setObservable(true, 0.5f);
	}
	static class StepSlashDodge extends StepMeleeSlash{
		
		public StepSlashDodge(Skill parent, String name, String desc, float timeLoad,
				float timeExecute, float timeCooldown, float baseDamage_) {
			super(parent, name, desc, timeLoad, timeExecute, timeCooldown, baseDamage_);
			chanceDodge = 0.6f;
			type =ActionType.Dodge;
		}
		@Override
		public float getAgiBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
		
		@Override
		public float getStrBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
	}
	
	
}
