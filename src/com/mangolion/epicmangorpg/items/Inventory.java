package com.mangolion.epicmangorpg.items;

import java.util.LinkedList;

public class Inventory {
	public LinkedList<ItemStack> itemStacks = new LinkedList<ItemStack>();
	
	public void addItem(Item item, int quantity){
		for (ItemStack itemStack: itemStacks){
			if (itemStack.item == item){
				itemStack.stack += quantity;
				return;
			}
		}
		
		ItemStack stack = new ItemStack(this, item, quantity);
		itemStacks.add(stack);
	}
	
	public boolean removeItem(Item item, int quantity){
		for (ItemStack itemStack: itemStacks){
			if (itemStack.item == item){
				if (itemStack.stack < quantity)
					return false;
				itemStack.stack -= quantity;
				if (itemStack.stack == 0)
					itemStacks.remove(itemStack);
				return true;
			}
		}
		return false;
	}
	
	public ItemStack getItem(String name){
		for (ItemStack itemStack : itemStacks)
			if (itemStack.item.name.toLowerCase().replace(" ", "").equals(name))
				return itemStack;
		return null;
	}
	
}
