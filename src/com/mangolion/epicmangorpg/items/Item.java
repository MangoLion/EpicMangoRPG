package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.characters.Character;

public abstract class Item {
	public  enum Type{
		ChangeStat,
		Damage,
		Heal,
		Craft,
		Equip,
		Custom, 
		Ammunition
	}
	
	public Type type;
	
	public String name, desc;
	public int maxStack;
	public float size, weight;
	public boolean consumable = false;
	
	public Item(String name, String desc, Type type, int maxStack) {
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.maxStack = maxStack;
	}
	
	public void onUse(Character character){
		onUse(character, null);
	}
	
	public void onUse(Character character, Character target){
		
	}

	public void onUse(Character character, float value){
		
	}
	
public void onUse(Character character, Character target, float value){
		
	}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return name;
}
}
