package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Game;

public class ItemPortalStone extends Item{

	public ItemPortalStone() {
		super("Portal Stone", "Can be used twice, first, it will teleport you to the ground floor, leaving another saved portal stone with the previous location,", Type.Custom, 10, 10);
		setConsumable(0.2f, 0.1f, 0, true);
	}
	
	@Override
	public void onUse(Character character, Character target, float value) {
		ItemPortalStoneSaved portal = new ItemPortalStoneSaved();
		portal.values.add(Game.getInstance().currentFloor);
		portal.values.add(Game.getInstance().floorPercent);
		CharacterPlayer.instance.inventory.addItem(portal);
		
		CharacterPlayer.instance.reset();
		Game.getInstance().currentFloor = 0;
		Game.getInstance().floorPercent = 0;
		Game.getInstance().begin();
				
	}

}
