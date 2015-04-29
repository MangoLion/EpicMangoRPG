package com.mangolion.epicmangorpg.weapons;


public class BasicRifle extends Weapon{

	public BasicRifle() {
		super("Basic Rifle", 5, 7, 1, 1, 1, Weapons.Gun);
		setReload(12, 1, 3);
		gunDamage = 15;
	}

}
