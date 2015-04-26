package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventWebFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillWebFiring extends Skill {

	public SkillWebFiring() {
		super("Web Shoot", "Shoot spider webs, can slow.",Weapons.ALL, ActionType.RangeNormal);
		addSteps(new Step(this, "Web Shoot", "",ActionType.RangeNormal, 0.3f, 0.2f, 0.2f, 0){
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventWebFire(1, getCharacter(), target, 20, this));
				super.execute(character);
			}			
		}.setCost(15, 10, 0, 0));
	}

}
