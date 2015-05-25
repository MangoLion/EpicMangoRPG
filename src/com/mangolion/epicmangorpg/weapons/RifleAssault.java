package com.mangolion.epicmangorpg.weapons;


public class RifleAssault extends Weapon{

	public RifleAssault() {
		super("Assault Rifle", 5, 7, 1, 1, 1, Weapons.Gun, Weapons.Reloadable, Weapons.LauncherGrenade);
		setReload(12, 0.6f, 3, 0.08f);
		gunDamage = 20;
		launcherDamage  = 45;
		price = 30;
	}

}
