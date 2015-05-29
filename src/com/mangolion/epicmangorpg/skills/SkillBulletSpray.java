package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBulletSpray extends Skill {

	public SkillBulletSpray() {
		super("Bullet Spray", "Empties the remaining bullets in your clip to all enemies, very rapidly but low accuracy",Weapons.Gun, ActionType.RangeNormal);
		shopPrice = 80;
		hasTarget = false;
		addSteps(new Step(this, "Bullet Spray", "",ActionType.RangeNormal, 0.3f, 0.1f, 0f,1){
			
			public void init() {
				useAmmo = true;
				chooseRandomTarget = true;
				setEvents(new EventRange("Bullet", "", 0.3f, getCharacter(), null, 0, this));
			};
			
			public void load() {
				getCharacter().weapon.isAutomatic = true;
				if ( getCharacter().weapon.ammo >= getCharacter().weapon.fireRate*1.5)
					ammoUse =(int) Math.ceil((getCharacter().weapon.fireRate*1.5));
				else
					ammoUse = getCharacter().weapon.ammo;
				super.load();

			}
	
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getSPBuff() {
				return prof*15;
			};
			
			public void cooldown() {
				if (checkCondition())
					getCharacter().skillCurrent.isCooldown = false;
				super.cooldown();
			};
		}.setCost(15, 0, 0, 0).setChances(1, 1, 0.05f));
	}

}
