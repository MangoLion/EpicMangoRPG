package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.characters.Character;

public class ItemStack {
	public Item item;
	public int stack = 0;
	public Inventory inventory;
	
	public ItemStack(Inventory inv, Item item, int stack) {
		this.item= item;
		this.stack= stack;
		inventory = inv;
		
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
			return item.name + " (" + stack + ")";
	}
}
