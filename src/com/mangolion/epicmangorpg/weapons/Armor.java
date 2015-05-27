package com.mangolion.epicmangorpg.weapons;

import com.mangolion.epicmangorpg.components.StatBuff;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;

public class Armor extends ItemCustom implements StatBuff{
	public static final int ACCESSORY = 0,
			HEAD = 1,
			BODY = 2,
			LEG = 3,
			FEET = 4,
			ROBE = 5,
			HAND = 6;
	
	public String name;
	public float baseDamage, speedModifier = 1, meleeDamageModifier, baseMagicDmg = 0, baseMagicDmgMod = 1, deffBuff, protBuff,agiBuff;
	public int type;
	
	public Armor(String name, String desc, float maxDurability, int type, float deffBuff, float protBuff, float agiBuff) {
		super(name, desc, ItemCustom.Type.Armor);
		this.name = name;
		this.maxDurability = maxDurability;
		this.durability = maxDurability;
		this.type = type;
		this.deffBuff = deffBuff;
		this.protBuff = protBuff;
		this.agiBuff = agiBuff;
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
		return agiBuff;
	}

	@Override
	public float getDefBuff() {
		// TODO Auto-generated method stub
		return deffBuff;
	}

	@Override
	public float getProtBuff() {
		// TODO Auto-generated method stub
		return protBuff;
	}

	@Override
	public float getAccuracyBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCriticalBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBalCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMeleeDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getGunDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCylinderDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBowDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMagicDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMeleeSpeedBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMagicSpeedBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMagicDefBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBarrierNegate() {
		// TODO Auto-generated method stub
		return 0;
	}
}
