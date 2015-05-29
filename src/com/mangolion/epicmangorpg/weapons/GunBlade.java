package com.mangolion.epicmangorpg.weapons;


public class GunBlade extends Weapon{

	public GunBlade() {
		super("Gun Blade", 15, 10, 1, 1, 1, Weapons.Gun, Weapons.Reloadable, Weapons.LauncherGrenade, Weapons.Bladed, Weapons.Sword);
		setReload(6, 0.5f, 2, 0.08f);
		isAutomatic = true;
		canToggleAutomatic = false;
		gunDamage = 25;
		launcherDamage  = 55;
		price = 30;
	}

}
