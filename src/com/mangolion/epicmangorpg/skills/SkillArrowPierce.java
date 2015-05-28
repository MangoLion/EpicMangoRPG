package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepStab;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArrowPierce extends Skill {

	public SkillArrowPierce() {
		super("Piercing Arrow", "Fires an arrow assisted with magic that can pierce armor, ignores 20% of target defense",Weapons.Bow, ActionType.RangeNormal) ;
		setObservable(true, 0.7f);
		shopPrice = 50;
		addSteps(new StepStab(this, "Piercing Arrow", "", 0.7f, 0.1f, 0f, 2f){
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getSPBuff() {
				return prof*15;
			};
			
			public float getDexBuff() {
				return prof*15;
			};
			
			public float getIntBuff() {
				return prof*10;
			};
			
			public void init() {
				chanceBlock = 0;
				setEvents(new EventArrow(0.5f, getCharacter(), null, 10, this));
			};
			
			public boolean checkConndition() {
				if (getCharacter().inventory.getItemNumber(Items.arrow) > 0)
					return super.checkConndition();
				return false;
			};
			
			public float getDamage() {
				if (getCharacter().target != null)
					return super.getDamage() + getCharacter().target.getDef()*0.2f;
				else
					return super.getDamage();
			};
			
			@Override
			public void execute(Character target) {
				getCharacter().inventory.removeItem(Items.arrow, 1);
				super.execute(target);
			}	
		}.setCost(25, 10, 10, 0));
	}
	
}
