package com.mangolion.epicmangorpg.ais;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;

public class AIFlight extends AISimple{

	public AIFlight(Character character) {
		super(character);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() { 
		if (!character.isAirborne())
			if (character.getSkill(ActionType.Flight).getFirst().execute())
				return;
		super.nextAction();
	}
}
