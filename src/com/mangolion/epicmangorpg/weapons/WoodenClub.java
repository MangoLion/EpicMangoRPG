package com.mangolion.epicmangorpg.weapons;


public class WoodenClub extends Weapon{

	public WoodenClub() {
		super("Wooden Club", 15, 6, 0.85f, 1.1f, 1.1f, Weapons.Blunt);
		price = 5;
	}
	
	@Override
	public float getDexBuff() {
		// TODO Auto-generated method stub
		return -5;
	}
}
