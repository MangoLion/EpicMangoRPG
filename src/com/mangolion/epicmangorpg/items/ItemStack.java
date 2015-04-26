package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.characters.Character;

public class ItemStack {
	public Item item;
	public ItemCustom itemCustom;
	public int stack = 0;
	public Inventory inventory;
	public boolean isCustom = false;
	
	public ItemStack(Inventory inv, Item item, int stack) {
		this.item= item;
		this.stack= stack;
		inventory = inv;
		
	}
	
	public ItemStack(Inventory inv, ItemCustom item) {
		this.itemCustom= item;
		this.stack= 1;
		inventory = inv;
		isCustom = true;
	}
	
	public void use(Character character){
		use(character, null);
	}
	
	public void use(Character character, Character target){
		item.onUse(character, target);
		stack --;
		if (stack <= 0)
			remove();
	}

	public void use(Character character, float value){
		item.onUse(character, value);
		stack --;
		if (stack <= 0)
			remove();
	}
	
public void use(Character character, Character target, float value){
		item.onUse(character, target, value);
		stack --;
		if (stack <= 0)
			remove();
	}

	public void remove(){
			inventory.itemStacks.remove(this);
	}
	
	@Override
	public String toString() {
		if (isCustom)
			return itemCustom.name + "(dur: " + itemCustom.durability + ")";
		else
			return item.name + "(" + stack + ")";
	}
}
