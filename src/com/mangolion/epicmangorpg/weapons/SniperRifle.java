package com.mangolion.epicmangorpg.weapons;


public class SniperRifle extends Weapon{

	public SniperRifle() {
		super("Sniper Rifle", 5, 7, 1.2f, 1, 1, Weapons.Gun, Weapons.Reloadable);
		setReload(5, 0.6f, 1, 0.08f);
		gunDamage = 25;
		canToggleAutomatic = false;
	}
	
	@Override
	public float getAccuracyBuff() {
		// TODO Auto-generated method stub
		return 0.2f;
	}
	
	@Override
	public float getCriticalBuff() {
		// TODO Auto-generated method stub
		return 0.2f;
	}

}
