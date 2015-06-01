package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;

public class Buff {
	public String name;
	public float time, value[] = new float[1];
	public Type types[] = new Type[1];
	public boolean notify = true;
	
	public GenType genType;
	public Element element;
	
	public Buff(String name, float value, float time, GenType genType, Type type) {
		this.name = name; 
		this.value[0] = value;
		this.types[0] = type;
		this.time = time;
		this.genType = genType;
	}
	
	public Buff(String name, float time, GenType genType, Type ... type) {
		this.name = name;
		this.types = type;
		this.time = time;
		this.genType = genType;
	}
	
	public Buff(String name, float time, GenType genType, boolean notify_, Type ... type) {
		this.name = name;
		this.types = type;
		this.time = time;
		this.genType = genType;
		notify = notify_;
	}
	
	public Buff setValue (float ... values){
		value = values;
		return this;
	}
	
	public Buff setElement(Element element){
		this.element = element;
		return this;
	}
	
	public float getValue(Type type){
		for (int i = 0; i < types.length; i ++){
			Type t = types[i];
			if (t == type)
				return value[i];
		}
		return -1;
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
		magicDef,
		prot,
		hpRegen,
		mpRegen,
		spRegen,
		balRegen,
		elemental,
		crit,
		airborne,
		accuracy,
		hpCost,
		spCost,
		mpCost,
		balCost,
		barrierNegate
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
