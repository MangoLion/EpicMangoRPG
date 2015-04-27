package com.mangolion.epicmangorpg.weapons;

import com.mangolion.epicmangorpg.components.StatBuff;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;

public class Weapon extends ItemCustom implements StatBuff{
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

	@Override
	public float getHPBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMPBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSPBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBalBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHPRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMPRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSPRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBalRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDexBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getIntBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getStrBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAgiBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDefBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getProtBuff() {
		// TODO Auto-generated method stub
		return 0;
	}
}
