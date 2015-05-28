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
		addSteps(new Step(this, "Swift Arrow", "",ActionType.RangeNormal, 0.5f, 0.1f, 0,1f){
			
			public void init() {
				setEvents(new EventArrow(0.3f, getCharacter(), null, 20, this));
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
		}.setCost(10, 0, 0, 0).setElement(new Element("Wind", 1)));
	}
	
}
