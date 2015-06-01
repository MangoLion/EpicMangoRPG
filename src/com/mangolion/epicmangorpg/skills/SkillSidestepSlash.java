package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSidestepSlash extends Skill {
	Random rand = new Random();


	public SkillSidestepSlash() {
		super("Sidestep Slash", "Allows for a sidestep and attack at the same time.",Weapons.Bladed, ActionType.Dodge);
		shopPrice = 30;
		addSteps(new StepSlashDodge(this, "Sidestep Slash", "",0.3f,
				0.4f, 0.2f, 0.6f) {

		}.setCost(20, 0, 8, 0));
		setObservable(true, 0.5f);
	}
	static class StepSlashDodge extends StepMeleeSlash{
		
		public StepSlashDodge(Skill parent, String name, String desc, float timeLoad,
				float timeExecute, float timeCooldown, float baseDamage_) {
			super(parent, name, desc, timeLoad, timeExecute, timeCooldown, baseDamage_);
			chanceDodge = 0.6f;
			type =ActionType.Dodge;
		}
		
		public void execute(Character target, float time, String aug) {
			getCharacter().applyBuff(new Buff("Evasion", getExecutionTime(), GenType.positive, false, Buff.Type.agi).setValue(2));
			super.execute(target, time, aug);
		};
		
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
