package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillShootArrow extends Skill {

	public SkillShootArrow() {
		super("Shoot Arrow", "Pulls back the bowstring and let loose an arrow, standard bow attack.",Weapons.ALL, ActionType.RangeNormal);
		addSteps(new Step(this, "Shoot Arrow", "",ActionType.RangeNormal, 0.7f, 0.1f, 0.15f, 0, 00, 0, 20,1){
			
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventArrow(0.5f, getCharacter(), target, 30, this));
				super.execute(character);
			}			
		});
	}

}
