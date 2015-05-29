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

public class SkillGunShoot extends Skill {

	public SkillGunShoot() {
		super("Shoot", "Takes proper aim and shoot, standard gun attack.",Weapons.Gun, ActionType.RangeNormal);
		addSteps(new Step(this, "Shoot", "",ActionType.RangeNormal, 0.5f, 0.1f, 0.15f,1){
			
			public void init() {
				useAmmo = true;
				setEvents(new EventRange("Bullet", "", 0.3f, getCharacter(), null, 0, this));
			};
			
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getSPBuff() {
				return prof*15;
			};
			
			public float getDexBuff() {
				return prof*10;
			};
		}.setCost(5, 0, 0, 0).setChances(1, 1, 0f));
	}

}
