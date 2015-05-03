package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.ItemStack;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepDodge;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillUseItem extends Skill{

	public SkillUseItem() {
		super("Use Item", ".",Weapons.ALL, ActionType.None);
		hasArg = true;		
		hasTarget = false;
		addSteps(new Step(this, "Use Item", "", ActionType.None, 0.1f, 0.1f, 0.1f, 0) {
			 ItemStack	stack;
			@Override
			public boolean checkConndition(String arg) {
			 stack = character.inventory.getItem(arg);
				if (stack != null){
					setUseItem(stack.item, 1);
					return super.checkConndition(arg);
				}
				Utility.narrate("You don't have [" + arg + "]" );
				return false;
			}
			
			@Override
			public void execute(Character target, float time, String aug) {
				stack.use(character, target);
				super.execute(target, time, aug);
			}
			
			@Override
			public float getLoadTime() {
				// TODO Auto-generated method stub
				return item.loadTime*(1-prof);
			}
			
			@Override
			public float getExecutionTime() {
				// TODO Auto-generated method stub
				return item.executeTime*(1-prof);
			}
			
			@Override
			public float getCooldownTime() {
				// TODO Auto-generated method stub
				return item.cooldownTime*(1-prof);
			}
		}.setCost(0, 0, 0, 0));
	}
	
	@Override
	public LinkedList<String> getArguments() {
		LinkedList<String>results = new LinkedList<String>();
		for (ItemStack stack: character.inventory.itemStacks)
			if (stack.item.consumable)
				results.add(stack.item.name);
		return results;
	}
}
