package com.mangolion.epicmangorpg.skills;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillCombatMastery  extends Skill{

	public SkillCombatMastery() {
		super("Combat Mastery", "", Weapons.ALL, ActionType.WeaponMastery);
	}

	@Override
	public float getHPBuff() {
		
		return Utility.format4(prof*50f);
	}
	
	@Override
	public float getSPBuff() {
		
		return Utility.format4(prof*50f);
	}
	
	@Override
	public float getStrBuff() {
		// TODO Auto-generated method stub
		return Utility.format4(prof*10f);
	}
	
	@Override
	public float getIntBuff() {
		// TODO Auto-generated method stub
		return Utility.format4(prof*10f);
	}
	
	@Override
	public float getAgiBuff() {
		// TODO Auto-generated method stub
		return Utility.format4(prof*5f);
	}
	
	@Override
	public float getDexBuff() {
		// TODO Auto-generated method stub
		return Utility.format4(prof*5f);
	}
	
	@Override
	public float getBalBuff() {
		// TODO Auto-generated method stub
		return Utility.format4(prof*50f);
	}

	
}
