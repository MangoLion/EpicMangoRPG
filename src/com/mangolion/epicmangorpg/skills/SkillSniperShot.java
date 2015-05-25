package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSniperShot extends Skill {

	public SkillSniperShot() {
		super("Sniper Shot", "Focus and takes time aiming at target's weakspot, higher crit chance. The weapon's automatic mode is automatically turned off.",Weapons.Gun, ActionType.RangeNormal);
		shopPrice = 30;
		addSteps(new Step(this, "Sniper Shot", "",ActionType.RangeNormal, 0.9f, 0.1f, 0.1f,1.5f){
			
			public boolean checkConndition() {
				if (getCharacter().weapon.canToggleAutomatic || !getCharacter().weapon.isAutomatic)
					return super.checkConndition();
				Utility.narrate(getCharacter().name + "'s weapon cannot be toggled.");
				return false;
			};
			
			public void init() {
				useAmmo = true;
			};
			
			@Override
			public void execute(Character target) {
				critBase = 0.2f * (1 + prof/2);
				chanceMiss -= prof/2;
				getCharacter().weapon.isAutomatic = false;
				for (int i = 0; i < ammoUse; i ++)
					Event.addEvent(new EventRange("Bullet", "", 0.2f, getCharacter(), target, 0, this));
				super.execute(target);
			}	
			
			public float getDexBuff() {
				return prof*15;
			};
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getSPBuff() {
				return prof*15;
			};
		}.setCost(5, 0, 0, 0).setChances(1, 1, -0.15f));
		setObservable(true, 0.7f);
	}

}
