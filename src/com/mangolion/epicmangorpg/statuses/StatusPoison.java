package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;

public class StatusPoison extends Status{

	float lastReport = 0, reportRate = 1, dps = 30;
	
	public StatusPoison(Character character_, float time_) {
		super(character_, "poisoned", time_);
		
	}
	
	@Override
	public void tick(float deltaTime) {
		character.setDamage(name, deltaTime*dps, true);
		super.tick(deltaTime);
	}

}
