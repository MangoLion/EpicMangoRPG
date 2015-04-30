package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillFlight extends Skill{

	public SkillFlight() {
		super("Flight", "", Weapons.ALL, ActionType.Flight);
		addSteps(new Step(this, "Flight", "", ActionType.Flight, 0.4f, 0.1f, 0, 0) {
			@Override
			public void execute(Character target, float time) {
				getCharacter().setAirborne(10);
				super.execute(target, time);
			}
		});
	}

}
