package com.mangolion.epicmangorpg.weapons;

import com.mangolion.epicmangorpg.components.Element;


public class WandPine extends Weapon{

	public WandPine() {
		super("Pine Wand", 3, 10, 1, 1, 1,  Weapons.Wand, Weapons.Blunt);
		price = 15;
		magicDamage = 5;
		baseMagicDmgMod = 1.2f;
		setElements(new Element("Fire", 0.3f));
		baseMagicDmgMod = 1.2f;
	}

}
