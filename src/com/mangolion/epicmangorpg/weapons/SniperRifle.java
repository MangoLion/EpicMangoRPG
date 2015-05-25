package com.mangolion.epicmangorpg.weapons;


public class SniperRifle extends Weapon{

	public SniperRifle() {
		super("Sniper Rifle", 5, 7, 1.2f, 1, 1, Weapons.Gun, Weapons.Reloadable);
		setReload(6, 0.6f, 2, 0.08f);
		gunDamage = 40;
		price = 40;
		//canToggleAutomatic = false;
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
