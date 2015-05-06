package com.mangolion.epicmangorpg.weapons;


public class BasicMachinegun extends Weapon{

	public BasicMachinegun() {
		super("Basic Machinegun", 5, 7, 0.7f, 1, 1, Weapons.Gun, Weapons.Reloadable);
		setReload(32, 0.6f, 6,0);
		gunDamage = 10;
		canToggleAutomatic =false;
		isAutomatic = true;
		price = 100;
	}
	
	@Override
	public float getAccuracyBuff() {
		// TODO Auto-generated method stub
		return -0.1f;
	}

}
