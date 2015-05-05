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
	public float size, weight, loadTime, executeTime, cooldownTime, price;
	public boolean consumable = false, hasTarget = false;
	
	public Item(String name, String desc, Type type, int maxStack, float price) {
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.maxStack = maxStack;
		this.price = price;
	}
	
	public void setConsumable(float load, float execute, float cd, boolean hastTarget){
		consumable = true;
		loadTime = load;
		executeTime = execute;
		cooldownTime = cd;
		this.hasTarget =hastTarget; 
	}
	
	public void onUse(Character character){
		onUse(character, null);
	}
	
	public void onUse(Character character, Character target){
		onUse(character, target, 0);
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
