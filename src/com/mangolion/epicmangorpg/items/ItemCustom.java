package com.mangolion.epicmangorpg.items;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;

public class ItemCustom {
	public  enum Type{
		Craft,
		Weapon,
		Armor
	}
	
	public Type type;
	
	public String name, desc;
	public float size, weight, durability, maxDurability, quality = 1;
	public LinkedList<Object> values = new LinkedList<Object>();
	public boolean consumable = false;
	
	public ItemCustom(String name, String desc, Type type) {
		this.name = name;
		this.desc = desc;
		this.type = type;
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
