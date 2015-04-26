package com.mangolion.epicmangorpg.weapons;

import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;

public class Weapon extends ItemCustom{
	public String name;
	public float baseDamage, speedModifier, sizeModifier, meleeDamageModifier, baseMagicDmg = 0, baseMagicDmgMod = 1;
	public Weapons type;
	
	public Weapon(String name, float baseDamage, float maxDurability, Weapons type, float meleespeedModifier, float sizeModifier, float meleeDmgMod) {
		super(name, "", ItemCustom.Type.Weapon);
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
