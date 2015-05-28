package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockExecute;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBlock extends Skill {
	
	
	public SkillBlock() {
		super("Block", "Can block and reduce damage from most attacks. Formula is def =  50 + character.maxHP*0.1",Weapons.ALL, ActionType.MeleeBlock);
		customTime = true;
		hasTarget = false;
		addSteps(new Step(this, "Block", "",ActionType.MeleeBlock, 0.4f, -1, 0.4f,0) {
public void execute(Character target, float time) {
	super.execute(target, time);
	value = (float) (50 + character.getMaxHP()*0.1)*(1 + prof);
};
	public float getHPBuff() {
		return prof * 50;
	};
	
	public float getDefBuff() {
		return prof*20;
	};
	
	public float getProtBuff() {
		return prof*10;
	};
	
	public float getStrBuff() {
		return prof*5;
	};
	
	public float getBalBuff() {
		return prof*10;
	};
			
			public boolean isCustomTime() {
				return true;
			};
	}.setMessages(new MsgMeleeBlockLoad(), new MsgMeleeBlockExecute(), new MsgBasicCD(), null)
	.setCost(15, 0, 0, 0));
	}
	
	@Override
	public boolean checkCompatability(Skill skill) {
		Step step = skill.steps.getFirst();
		return step.chanceBlock > 0;
	}
	
}
