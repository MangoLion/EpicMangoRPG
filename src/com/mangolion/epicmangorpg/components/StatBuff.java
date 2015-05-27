package com.mangolion.epicmangorpg.components;

import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;

public abstract interface StatBuff {
	public float getHPBuff();
	public float getMPBuff();
	public float getSPBuff();
	public float getBalBuff();
	public float getHPRegenBuff();
	public float getMPRegenBuff();
	public float getSPRegenBuff();
	public float getBalRegenBuff();
	public float getHPCostBuff();
	public float getMPCostBuff();
	public float getSPCostBuff();
	public float getBalCostBuff();
	public float getDexBuff();
	public float getIntBuff();
	public float getStrBuff();
	public float getAgiBuff();
	public float getDefBuff();
	public float getMagicDefBuff();
	public float getProtBuff();
	public float getAccuracyBuff();
	public float getCriticalBuff();
	public float getMeleeDmgBuff();
	public float getGunDmgBuff();
	public float getCylinderDmgBuff();
	public float getBowDmgBuff();
	public float getMagicDmgBuff();
	public float getMeleeSpeedBuff();
	public float getMagicSpeedBuff();
	public float getBarrierNegate();
}
