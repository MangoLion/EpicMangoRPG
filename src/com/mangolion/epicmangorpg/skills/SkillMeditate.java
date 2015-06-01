package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.messages.MsgParryLoad;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillMeditate extends Skill{

	public SkillMeditate() {
		super("Meditate", "Recovers mp 4 times as fast  while int is left at 30% for x seconds",Weapons.ALL, ActionType.RecoverMP);
		addSteps(new StepMeditate(this, 0.1f, 0.0f, 0.0f).setCost(0, 0, 0, 0));
		customTime = true;
		hasTarget = false;
	}

	public static class StepMeditate extends Step{
		public float chanceParry = 0.8f;
		public StepMeditate(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Meditate", "",ActionType.RecoverMP, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name relaxes $p3 body and recover $p3 energy"), null, new MsgBasicCD(), null);

		}
		@Override
		public void init() {
			setBuff(new Buff("Meditate",3, customTime, GenType.neutral, Buff.Type.mpRegen));
			super.init();
		}
		
		public float getMPBuff() {
			// TODO Auto-generated method stub
			return prof * 15;
		}
		
		@Override
		public float getIntBuff() {
			// TODO Auto-generated method stub
			return prof * 15;
		}
		
		float customTime = 0;
		
		@Override
		public void load() {
			if (getCharacter().isPlayer)
				customTime = customExecutionTime;
			else
				customTime = 2;
			customExecutionTime = 0;
			super.load();
		}
		
		@Override
		public boolean isCustomTime() {
			// TODO Auto-generated method stub
			return true;
		}
		
		public boolean checkConndition() {
			for (Buff buff: getCharacter().buffs)
				if (buff.name.equals(name)){
					if (getCharacter() == CharacterPlayer.instance)
						Utility.narrate("You can only have one buff of the same type");
					return false;
				}
			return super.checkConndition();
		};
		
		@Override
		public void execute(Character target, float time) {
			//System.out.println("" + -getCharacter().str*0.7f);
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Meditate Debuff", -getCharacter().getInt()*0.7f*(1 - prof ) , customTime, GenType.negative, Buff.Type.inte));
			super.execute(target, time);
		}

	}
}
