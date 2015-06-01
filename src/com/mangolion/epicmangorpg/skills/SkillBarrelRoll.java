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
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBarrelRoll extends Skill{

	public SkillBarrelRoll() {
		super("Barrel Roll", "Barrelrollling to dodge long barrage of range attacks, works on melee attacks too, cannot dodge AOE skills. While it has a long execution time, the user looses alot of balance and it takes a longer cooldown.",Weapons.ALL, ActionType.Dodge);
		hasTarget = false;
		addSteps(new StepDodge("Barrel roll", this, 0.5f, 1.2f, 0.5f).setCost(30, 0, 30, 0));
		setObservable(true, 1);
	}

	public static class StepDodge extends Step{
		public float value = 0.5f;
		public StepDodge(String name, Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent,name,"", ActionType.Dodge, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new MsgDodgeLoad(), new MsgDodgeExecute(), new MsgBasicCD(), null);
			
		}
		
		public void execute(Character target, float time, String aug) {
			getCharacter().applyBuff(new Buff("Evasion", getExecutionTime(), GenType.positive, false, Buff.Type.agi).setValue(2));
			super.execute(target, time, aug);
		};
		
		@Override
		public float getAgiBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
		
		@Override
		public float getBalBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
		
		@Override
		public float getSPBuff() {
			// TODO Auto-generated method stub
			return prof*10;
		}
		
	}
}
