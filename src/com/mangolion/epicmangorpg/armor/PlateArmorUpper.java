package com.mangolion.epicmangorpg.armor;

import com.mangolion.epicmangorpg.weapons.Armor;

public class PlateArmorUpper extends Armor{

	public PlateArmorUpper() {
		super("Plate Armor Upper", "", 15, BODY, 15,7, -8);
		speedModifier = 1.05f;
		price = 16;
	}

}
