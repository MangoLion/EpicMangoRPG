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

public class SkillArrowFlame extends Skill {

	public SkillArrowFlame() {
		super("Flaming Arrow", "Fires an arrow lit on fire using  magic.",Weapons.Bow, ActionType.RangeNormal) ;
		setObservable(true, 0.7f);
		shopPrice = 60;
		addSteps(new Step(this, "Flaming Arrow", "", ActionType.RangeNormal, 0.6f, 0.1f, 0.1f,  2f){
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			
			public void init() {
				setEvents(new EventArrow(0.5f, character, null, 10, this));
			};
			
			public boolean checkConndition() {
				if (getCharacter().inventory.getItemNumber(Items.arrow) > 0)
					return super.checkConndition();
				return false;
			};
			
			public float getDexBuff() {
				return prof*10;
			};
			
			public float getIntBuff() {
				return prof*10;
			};

			public float getSPBuff() {
				return prof*15;
			};
			@Override
			public void execute(Character target) {
				getCharacter().inventory.removeItem(Items.arrow, 1);
				super.execute(target);
			}	
		}.setCost(20, 15, 10, 0).setElement(new Element("Fire", 1)));
	}
	
}
