package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSidestepShoot extends Skill {
	Random rand = new Random();


	public SkillSidestepShoot() {
		super("Sidestep Shoot", "Allows for a sidestep and shoot at the same time.",Weapons.Reloadable, ActionType.Dodge);
		addSteps(new StepShootDodge(this, "Sidestep Shoot", "",0.3f,
				0.4f, 0.2f, 1) {

		}.setCost(20, 0, 8, 0));
		setObservable(true, 0.5f);
	}
	static class StepShootDodge extends Step{
		
		public StepShootDodge(Skill parent, String name, String desc, float timeLoad,
				float timeExecute, float timeCooldown, float baseDamage_) {
			super(parent, name, desc, ActionType.Dodge, timeLoad, timeExecute, timeCooldown, baseDamage_);
			chanceDodge = 0.6f;
			useAmmo = true;
		}
		public boolean checkConndition() {
			if (getCharacter().inventory.getItemNumber(Items.arrow) > 0)
				return super.checkConndition();
			return false;
		};
		
		@Override
		public void execute(Character target) {
			Event.addEvent(new EventArrow(0.3f, getCharacter(), target, 20, this));
			getCharacter().inventory.removeItem(Items.bullet, 1);
			super.execute(target);
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