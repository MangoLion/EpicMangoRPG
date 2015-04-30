package com.mangolion.epicmangorpg.weapons;


public class BasicRifle extends Weapon{

	public BasicRifle() {
		super("Basic Semi-automatic Rifle", 5, 7, 1, 1, 1, Weapons.Gun, Weapons.Reloadable);
		setReload(12, 0.6f, 3, 0.08f);
		gunDamage = 10;
	}

}
