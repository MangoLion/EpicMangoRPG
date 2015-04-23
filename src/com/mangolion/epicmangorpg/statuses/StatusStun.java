package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;

public class StatusStun extends Status{

	public StatusStun(Character character_, float time_) {
		super(character_, "stunned", time_);
		doStun = true;
	}
	
	@Override
	public void tick(float deltaTime) {
		if (character.skillCurrent != null)
			character.skillCurrent.cancel();
		super.tick(deltaTime);
	}

}
