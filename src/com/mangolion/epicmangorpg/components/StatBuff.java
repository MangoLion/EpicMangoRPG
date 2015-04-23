package com.mangolion.epicmangorpg.components;

import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;

public interface StatBuff {
	public float getHPBuff();
	public float getMPBuff();
	public float getSPBuff();
	public float getBalBuff();
	public float getHPRegenBuff();
	public float getMPRegenBuff();
	public float getSPRegenBuff();
	public float getBalRegenBuff();
	public float getDexBuff();
	public float getIntBuff();
	public float getStrBuff();
	public float getAgiBuff();
	public float getDefBuff();
	public float getProtBuff();
}
