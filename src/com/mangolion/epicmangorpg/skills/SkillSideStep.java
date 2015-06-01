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

public class SkillSideStep extends Skill{

	public SkillSideStep() {
		super("Side Step", "Side stepping to dodge most melee skills, cannot dodge AOE skills. Its fast but very short.",Weapons.ALL, ActionType.Dodge);
		hasTarget = false;
		addSteps(new StepDodge("Sidestep", this, 0.2f, 0.3f, 0.2f){
			
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
			
			public void execute(Character target, float time, String aug) {
				getCharacter().applyBuff(new Buff("Evasion", getExecutionTime(), GenType.positive, false, Buff.Type.agi).setValue(2));
				super.execute(target, time, aug);
			};
		}.setCost(15, 0, 8, 0));
	}
}
