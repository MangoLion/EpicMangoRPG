package com.mangolion.epicmangorpg.weapons;

import com.mangolion.epicmangorpg.items.Item;

public class Weapon extends Item{
	public String name;
	public float baseDamage, maxDurability, durability, speedModifier, sizeModifier, meleeDamageModifier, baseMagicDmg = 0, baseMagicDmgMod = 1;
	public Weapons type;
	
	public Weapon(String name, float baseDamage, float maxDurability, Weapons type, float meleespeedModifier, float sizeModifier, float meleeDmgMod) {
		super(name, "", Item.Type.Equip);
		this.name = name;
		this.baseDamage = baseDamage;
		this.maxDurability = maxDurability;
		this.durability = maxDurability;
		this.type = type;
		this.speedModifier = meleespeedModifier;
		this.sizeModifier = sizeModifier;
		this.speedModifier = meleespeedModifier;
		meleeDamageModifier = meleeDmgMod;
	}
}
