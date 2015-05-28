package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArrowRapid extends Skill {

	public SkillArrowRapid() {
		super("Rapid Arrow", "Fires 3 arrows consecutively.",Weapons.Bow, ActionType.RangeNormal);
		setObservable(true, 0.7f);
		shopPrice = 20;
		addSteps(new Step(this, "First Arrow", "",ActionType.RangeNormal, 0.6f, 0.1f, 0,1f){
			
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
		}.setCost(10, 0, 0, 0),
		new Step(this, "Second Arrow", "",ActionType.RangeNormal, 0.2f, 0.1f, 0f,1.2f){
			
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
		}.setCost(10, 0, 0, 0),
		new Step(this, "Third Arrow", "",ActionType.RangeNormal, 0.3f, 0.1f, 0f,1.5f){
			
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
		}.setCost(10, 0, 0, 0));
	}

}
