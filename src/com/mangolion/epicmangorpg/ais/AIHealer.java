package com.mangolion.epicmangorpg.ais;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.skills.Skill;

public class AIHealer extends AISimple {

	public AIHealer(Character character) {
		super(character, 0.6f, 0.9f);
		recoverLevel = 0.9f;
	}

}
