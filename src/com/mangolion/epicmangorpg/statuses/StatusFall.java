package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Damage;
import com.mangolion.epicmangorpg.components.Element;

public class StatusFall extends Status{

	float damage = 0;
	
	public StatusFall(Character character_, float time_) {
		super(character_, "Falling", time_);
		damage = time_*15;
		doStun = true;
	}
	
	@Override
	public void tick(float deltaTime) {
		if (character.isAirborne())
			character.removeBuff("Airborne");
		
		super.tick(deltaTime);
	}
	
	@Override
	public void remove() {
		Damage damage = new Damage(character, this.damage, new Element("poison", 1));
		character.setDamage(damage);
		super.remove();
	}
	

}
