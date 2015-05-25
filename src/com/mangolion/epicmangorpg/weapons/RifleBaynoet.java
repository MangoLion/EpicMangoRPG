package com.mangolion.epicmangorpg.weapons;


public class RifleBaynoet extends Weapon{

	public RifleBaynoet() {
		super("Basic Semi-automatic Rifle with Baynoet", 8, 7, 1, 1, 1, Weapons.Gun, Weapons.Reloadable, Weapons.Bladed);
		setReload(12, 0.6f, 3, 0.08f);
		gunDamage = 17;
		price = 25;
	}

}
