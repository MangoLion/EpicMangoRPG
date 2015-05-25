package com.mangolion.epicmangorpg.weapons;


public class RifleBasic extends Weapon{

	public RifleBasic() {
		super("Basic Semi-automatic Rifle", 5, 7, 1, 1, 1, Weapons.Gun, Weapons.Reloadable);
		setReload(12, 0.6f, 3, 0.08f);
		gunDamage = 20;
		price = 25;
	}

}
