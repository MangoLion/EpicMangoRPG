package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Damage;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.messages.Msg;

public class StatusFall extends Status{

	float damage = 0;
	
	public StatusFall(Character character_, float time_) {
		super(character_, "Falling", time_);
		damage = time_*30;
		doStun = true;
	}
	
	@Override
	public void tick(float deltaTime) {
		if (character.isAirborne())
			character.removeBuff("Airborne");
		if (time*30 > damage)
			damage = time*30;
		super.tick(deltaTime);
	}
	
	@Override
	public void remove() {
		Damage damage = new Damage(character, this.damage, Damage.MELEE);
		StylePainter.append(new Msg("A loud thud can be heard as the ground breaks $name's fall").getMessage(character, null, 0));
		character.setDamage(damage);
		super.remove();
	}
	

}
