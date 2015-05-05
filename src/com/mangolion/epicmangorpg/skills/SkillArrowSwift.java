package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepStab;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArrowSwift extends Skill {

	public SkillArrowSwift() {
		super("Swift Arrow", "Fires an arrow as swift as the wind itself.",Weapons.Bow, ActionType.RangeNormal) ;
		setObservable(true, 0.7f);
		shopPrice = 30;
		addSteps(new Step(this, "Swift Arrow", "", ActionType.RangeNormal,0.3f, 0.1f, 0.1f, 0.7f){
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}

			public boolean checkConndition() {
				if (getCharacter().inventory.getItemNumber(Items.arrow) > 0)
					return super.checkConndition();
				return false;
			};
			
			public float getSPBuff() {
				return prof*15;
			};
			@Override
			public void execute(Character target) {
				EventRange event = new EventArrow(0.3f, character, target, 10, this);
				event.chanceBlock = 0;
				Event.addEvent(event);
				getCharacter().inventory.removeItem(Items.arrow, 1);
				super.execute(target);
			}	
		}.setCost(10, 0, 10, 0).setElement(new Element("Wind", 1)));
	}
	
}
