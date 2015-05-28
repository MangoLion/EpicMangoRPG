package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArrowRain extends Skill {

	public SkillArrowRain() {
		super("Arrow Rain", "",Weapons.Bow, ActionType.RangeNormal);
		setObservable(true, 0.7f);
		hasTarget = false;
		shopPrice = 80;
		addSteps(new Step(this, "Arrow Rain(First)", "",ActionType.RangeNormal, 01f, 0.1f, 0.1f,1){
			
			public void init() {
				setEvents(new EventArrow(0.7f, getCharacter(), null, 20, this));
				setUseItem(Items.arrow, 1);
				isAOE = true;
			};
			
			public float getDexBuff() {
				return prof*10;
			};
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
