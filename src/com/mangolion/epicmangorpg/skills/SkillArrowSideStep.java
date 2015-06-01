package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArrowSideStep extends Skill {
	Random rand = new Random();


	public SkillArrowSideStep() {
		super("Sidestep Arrow", "Allows for a sidestep and shoot arrow at the same time.",Weapons.Bow, ActionType.Dodge);
		setObservable(true, 0.7f);
		shopPrice = 40;
		addSteps(new StepShootDodge(this, "Sidestep Arrow", "",0.3f,
				0.4f, 0.2f, 0.6f) {
			public void execute(Character target, float time, String aug) {
				getCharacter().applyBuff(new Buff("Evasion", getExecutionTime(), GenType.positive, false, Buff.Type.agi).setValue(2));
				super.execute(target, time, aug);
			};
		}.setCost(20, 0, 8, 0));
		setObservable(true, 0.5f);
	}
	static class StepShootDodge extends Step{
		
		public StepShootDodge(Skill parent, String name, String desc, float timeLoad,
				float timeExecute, float timeCooldown, float baseDamage_) {
			super(parent, name, desc, ActionType.Dodge, timeLoad, timeExecute, timeCooldown, baseDamage_);
			chanceDodge = 0.6f;
		}
		public void init() {
			setEvents(new EventArrow(0.3f, getCharacter(), null, 20, this));
			setUseItem(Items.arrow, 1);
		};
		
		@Override
		public float getAgiBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
		
		public float getDexBuff() {
			return prof*10;
		};
		
		@Override
		public float getStrBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
	}
	
	
}
