package com.mangolion.epicmangorpg.components;

public enum ActionType {
	MeleeSwing(GeneralType.Attack),
	MeleeStab(GeneralType.Attack),
	MeleeBlock(GeneralType.Defend),
	MeleeParry(GeneralType.Defend),
	MeleeSpecial(GeneralType.Attack),
	Magic(GeneralType.Attack),
	RecoverHP(GeneralType.Recover),
	RecoverMP(GeneralType.Recover),
	RecoverSP(GeneralType.Recover),
	RecoverAmmo(GeneralType.Recover),
	RecoverJam(GeneralType.Recover),
	Dodge(GeneralType.Defend), 
	RangeNormal(GeneralType.Attack),
	Summon(GeneralType.Recover),
	WeaponMastery (GeneralType.Passive),
	Defend(GeneralType.Defend),
	None(GeneralType.None), 
	Flight(GeneralType.None);
	
	GeneralType type;
	private ActionType(GeneralType type) {
		this.type = type;
	}
	
	public GeneralType getGeneralType(){
		return type;
	}
}
