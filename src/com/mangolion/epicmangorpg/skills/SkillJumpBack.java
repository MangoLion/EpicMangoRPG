package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepDodge;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillJumpBack extends Skill{

	public SkillJumpBack() {
		super("Jump Back", "Jumping backwards to reduce 30% of the damage taken.",Weapons.ALL, ActionType.Defend);
		addSteps(new Step(this, "Jump Back", "",  ActionType.Defend, 0.1f, 0.3f, 0.4f, 0){
			
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
			
			@Override
			public void execute(Character target, float time) {
				addProf(new Proficiency(getCharacter(), getCharacter()));
				getCharacter().applyBuff(new Buff("Jump Back Boost", 0.3f, GenType.positive, Buff.Type.prot, Buff.Type.agi).setValue(30, 0.3f));
				super.execute(target, time);
			}
		}.setCost(20, 0, 30, 0));
	}
}
