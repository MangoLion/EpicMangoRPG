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
	Dodge(GeneralType.Defend), 
	RangeNormal(GeneralType.Attack),
	Summon(GeneralType.Recover),
	Passive (GeneralType.Passive),
	None(GeneralType.None);
	
	GeneralType type;
	private ActionType(GeneralType type) {
		this.type = type;
	}
	
	public GeneralType getGeneralType(){
		return type;
	}
}
