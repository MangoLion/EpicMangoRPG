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
		super("Shoot", "",Weapons.Gun, ActionType.RangeNormal);
		addSteps(new Step(this, "Shoot", "",ActionType.RangeNormal, 0.6f, 0.1f, 0.15f,1){
			
			public void init() {
				useAmmo = true;
			};
			
			@Override
			public void execute(Character target) {

				for (int i = 0; i < ammoUse; i ++)
					Event.addEvent(new EventRange("Bullet", "", 0.3f, getCharacter(), target, 0, this));
				super.execute(target);
			}			
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getSPBuff() {
				return prof*15;
			};
		}.setCost(15, 0, 0, 0));
	}

}
