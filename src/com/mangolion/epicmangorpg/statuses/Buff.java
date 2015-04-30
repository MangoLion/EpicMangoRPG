package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;

public class Buff {
	public String name;
	public float value, time;
	public Type types[];
	public GenType genType;
	public Element element;
	
	public Buff(String name, float value, float time, Type type, GenType genType) {
		this.name = name;
		this.value = value;
		Type temp[] = {type};
		this.types = temp;
		this.time = time;
		this.genType = genType;
	}
	
	public Buff(String name, float value, float time, GenType genType, Type ... type) {
		this.name = name;
		this.value = value;
		this.types = type;
		this.time = time;
		this.genType = genType;
	}
	
	public boolean checkType(Type type){
		for (Type t: types)
			if (t == type)
				return true;
		return false;
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
		strRegen,
		elemental,
		crit,
		airborne,
		accuracy
	}
	
	public static enum GenType{
		positive,
		negative,
		neutral
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + "(" + time + ")";
	}
}
