package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;

public class Buff {
	public String name;
	public float value, time;
	public Type type;
	public GenType genType;
	
	public Buff(String name, float value, float time, Type type, GenType genType) {
		this.name = name;
		this.type = type;
		this.time = time;
		this.genType = genType;
	}
	
	
	public static enum Type{
		hp,
		mp,
		sp,
		bal,
		inte,
		dex,
		str,
		agi,
		def,
		prot,
		hpRegen,
		mpRegen,
		spRegen,
		balRegen,
		intRegen,
		dexRegen,
		strRegen
	}
	
	public static enum GenType{
		positive,
		negative,
		neutral
	}
}
