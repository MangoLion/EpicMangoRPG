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
		addSteps(new Step(this, "First Arrow", "",ActionType.RangeNormal, 0.4f, 0.1f, 0.1f,1f){
			
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventArrow(0.5f, getCharacter(), target, 20, this));
				getCharacter().inventory.removeItem(Items.arrow, 1);
				super.execute(target);
			}			
			
			public boolean checkConndition() {
				if (getCharacter().inventory.getItemNumber(Items.arrow) > 0)
					return super.checkConndition();
				return false;
			};
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
			public float getSPBuff() {
				return prof*10;
			};
		}.setCost(10, 0, 0, 0),
		new Step(this, "Second Arrow", "",ActionType.RangeNormal, 0.3f, 0.1f, 0.1f,1.4f){
			
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventArrow(0.5f, getCharacter(), target, 20, this));
				getCharacter().inventory.removeItem(Items.arrow, 1);
				super.execute(target);
			}			
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
			public float getSPBuff() {
				return prof*10;
			};
		}.setCost(12, 0, 0, 0),
		new Step(this, "Second Arrow", "",ActionType.RangeNormal, 0.3f, 0.1f, 0.4f,2f){
			
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventArrow(0.5f, getCharacter(), target, 20, this));
				getCharacter().inventory.removeItem(Items.arrow, 1);
				super.execute(target);
			}			
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
			public float getSPBuff() {
				return prof*10;
			};
		}.setCost(15, 0, 0, 0));
	}

}
