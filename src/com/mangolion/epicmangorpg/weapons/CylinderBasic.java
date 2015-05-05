package com.mangolion.epicmangorpg.weapons;


public class CylinderBasic extends Weapon{

	public CylinderBasic() {
		super("Basic Cylinder", 5, 7, 1, 1, 1, Weapons.Cylinder, Weapons.Reloadable);
		setReload(12, 0.6f, 3, 1);
		alchemyDamage = 15;
		price = 20;
	}

}
