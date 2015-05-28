package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArrowShoot extends Skill {

	public SkillArrowShoot() {
		super("Shoot Arrow", "Pulls back the bowstring and let loose an arrow, standard bow attack.",Weapons.Bow, ActionType.RangeNormal);
		setObservable(true, 1);
		addSteps((new Step(this, " Arrow", "",ActionType.RangeNormal, 0.5f, 0.1f, 0,1f){
			
			public void init() {
				setEvents(new EventArrow(0.5f, getCharacter(), null, 20, this));
				setUseItem(Items.arrow, 1);
			};
			
			public float getDexBuff() {
				return prof*10;
			};

			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
			public float getSPBuff() {
				return prof*10;
			};
		}.setCost(10, 0, 0, 0)));
	}

}
