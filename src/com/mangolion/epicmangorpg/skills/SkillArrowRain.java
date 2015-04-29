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
		addSteps(new Step(this, "Arrow Rain(First)", "",ActionType.RangeNormal, 01f, 0.1f, 0.1f,1){
			
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventArrow(0.7f, getCharacter(), target, 10, this, true));
				getCharacter().inventory.removeItem(Items.arrow, (int) ( 10*(prof + 1)));
				super.execute(target);
			}			
			
			public boolean checkConndition() {
				if (getCharacter().inventory.getItemNumber(Items.arrow) > 0)
					return super.checkConndition();
				return false;
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
