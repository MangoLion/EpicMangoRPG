package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepDodge;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSideJump extends Skill{

	public SkillSideJump() {
		super("Side Jump", "Jumping to the side to dodge, cannot dodge AOE skills. Used as a last ditch effort since its very fast, but the user will loose all balance and has a long cooldown",Weapons.ALL, ActionType.Dodge);
		hasTarget = false;
		addSteps(new StepDodge("Side Jump", this, 0.1f, 0.6f, 0.6f){
			
			public void execute(Character target, float time, String aug) {
				getCharacter().applyBuff(new Buff("Evasion", getCharacter().getDex()*1.5f, getExecutionTime(), GenType.positive, Buff.Type.agi));
				super.execute(target, time, aug);
			};
			
			@Override
			public float getAgiBuff() {
				// TODO Auto-generated method stub
				return prof*20;
			}
			
			@Override
			public float getSPBuff() {
				// TODO Auto-generated method stub
				return prof*10;
			}
		}.setCost(30, 0, 30, 0));
		setObservable(true, 1);
	}


}
