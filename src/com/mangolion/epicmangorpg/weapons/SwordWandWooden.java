package com.mangolion.epicmangorpg.weapons;


public class SwordWandWooden extends Weapon{

	public SwordWandWooden() {
		super("Wooden Sword Wand", 10, 10, 1, 1, 1,  Weapons.Sword, Weapons.Wand, Weapons.Bladed);
		price = 15;
		magicDamage =7;
		baseMagicDmgMod = 1.1f;
	}

}
