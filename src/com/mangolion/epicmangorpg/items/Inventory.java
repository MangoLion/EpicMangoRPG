package com.mangolion.epicmangorpg.items;

import java.util.Iterator;
import java.util.LinkedList;

public class Inventory {
	public LinkedList<ItemStack> itemStacks = new LinkedList<ItemStack>();
	public LinkedList<ItemCustom> itemCustoms = new LinkedList<ItemCustom>();
	
	public void addItem(Item item, int quantity){
		int left = quantity;
		for (int i = 0; i < quantity; i ++)
		for (ItemStack itemStack: itemStacks){
			if (itemStack.item == item){
				while ( itemStack.stack < item.maxStack && left > 0){
				itemStack.stack ++;
				left --;
				}
			}
		}
		while (left > 0){
			ItemStack stack = new ItemStack(this, item, 0);
			while (left > 0 && stack.stack < item.maxStack){
				stack.stack ++;
				left --;
			}
			itemStacks.add(stack);
		}
	}
	
	public void addItem(ItemCustom item){
		itemCustoms.add(item);
	}
	
	public boolean removeItem(Item item, int quantity){
		if (getItemNumber(item) < quantity)
			return false;
		
		int left = quantity;
		Iterator<ItemStack> it = itemStacks.iterator();
		while (it.hasNext()){
			ItemStack itemStack = it.next();
			if (itemStack.item == item){
						while ( itemStack.stack  > 0 && left > 0){
						itemStack.stack --;
						left --;
						if (itemStack.stack <= 0)
							it.remove();
			}
		}
		}
		return true;
	}
	
	public int getItemNumber(Item item){
		int num = 0;
		for (ItemStack itemStack: itemStacks)
			if (itemStack.item == item)
				num ++;
		return num;
	}
	public ItemStack getItem(String name){
		for (ItemStack itemStack : itemStacks)
			if (itemStack.item.name.toLowerCase().replace(" ", "").equals(name))
				return itemStack;
		return null;
	}
	
}
