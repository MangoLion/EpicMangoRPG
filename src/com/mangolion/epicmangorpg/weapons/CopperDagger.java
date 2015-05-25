package com.mangolion.epicmangorpg.weapons;


public class CopperDagger extends Weapon{

	public CopperDagger() {
		super("Copper Dagger", 5, 6, 0.85f, 0.5f, 0.7f, Weapons.Dagger, Weapons.Bladed);
		price = 5;
	}
	
	@Override
	public float getCriticalBuff() {
		// TODO Auto-generated method stub
		return 0.2f;
	}

}
