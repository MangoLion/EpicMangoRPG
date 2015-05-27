package com.mangolion.epicmangorpg.weapons;

import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.StatBuff;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;

public class Weapon extends ItemCustom implements StatBuff{
	public String name;
	public float baseDamage, speedModifier, sizeModifier, meleeDamageModifier, magicSpeedMod = 0, baseMagicDmgMod = 1, alchemyMod = 1, gunMod = 1, reloadTime = 0, alchemyDamage = 0, gunDamage = 0, chanceJam = 0, bowDamage = 0, magicDamage = 0, launcherDamage = 0;
	public Weapons type[];
	public boolean useAmmo = false, isJammed = false, isAutomatic = false, canToggleAutomatic = true;
	public int ammo = 0, maxAmmo = 0, fireRate = 0;
	public Element elements[] = new Element[0];
	
	public Weapon(String name, float baseDamage, float maxDurability, float meleespeedModifier, float sizeModifier, float meleeDmgMod, Weapons... type) {
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
	
	public Weapon(String name, float baseDamage, float maxDurability, Weapons type, float meleespeedModifier, float sizeModifier, float meleeDmgMod) {
		super(name, "", ItemCustom.Type.Weapon);
		this.name = name;
		this.baseDamage = baseDamage;
		this.maxDurability = maxDurability;
		this.durability = maxDurability;
		Weapons[] temp =  {type};
		this.type =temp;
		this.speedModifier = meleespeedModifier;
		this.sizeModifier = sizeModifier;
		this.speedModifier = meleespeedModifier;
		meleeDamageModifier = meleeDmgMod;
	}
	
	public void setElements(Element ... elements){
		this.elements = elements;
	}
	
	public void setReload(int maxAmmo, float reloadTime, int fireRate, float jam){
		useAmmo = true;
		this.maxAmmo = maxAmmo;
		this.reloadTime = reloadTime;
		this.fireRate = fireRate;
		this.chanceJam = jam;
		ammo = maxAmmo;
	}
	
	public boolean checkType(Weapons type){
		for (Weapons w: this.type)
			if (w == type)
				return true;
		return false;
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
