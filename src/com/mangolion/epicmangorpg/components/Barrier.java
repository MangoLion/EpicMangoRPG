package com.mangolion.epicmangorpg.components;

public class Barrier implements StatBuff{
	enum Type{
		physical,
		magic,
		all
	}
	
	public String name;
	float hp, time, absorbPercent = 1;
	public Type type;
	
	public Barrier(String name, float hp, float time, float absorbPercent, Type type) {
		this.name = name;
		this.hp = hp;
		this.time = time;
		this.absorbPercent = absorbPercent;
		this.type = type;
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

}
