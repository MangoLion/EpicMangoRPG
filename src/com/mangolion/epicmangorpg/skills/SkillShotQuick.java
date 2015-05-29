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

public class SkillShotQuick extends Skill {

	public SkillShotQuick() {
		super("Quick Shot", "fires the moment the gun is poiting towards the target, fast but unaccurate",Weapons.Gun, ActionType.RangeNormal);
		addSteps(new Step(this, "Quick Shot", "",ActionType.RangeNormal, 0.3f, 0.1f, 0.15f,1){
			
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
			
			public float getAccuracyBuff() {
				return -0.1f;
			};
		}.setCost(5, 0, 0, 0).setChances(1, 1, 0f));
	}

}
