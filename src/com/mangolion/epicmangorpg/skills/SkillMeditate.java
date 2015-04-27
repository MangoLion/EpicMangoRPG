package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.components.Proficiency;
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
		super("Meditate", "Recovers mp 4 times as fast  while int is left at 30%.",Weapons.ALL, ActionType.RecoverMP);
		addSteps(new StepMeditate(this, 0.1f, 0.0f, 0.0f).setCost(0, 0, 0, 0));
	}

	public static class StepMeditate extends Step{
		public float chanceParry = 0.8f;
		public StepMeditate(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Meditate", "",ActionType.RecoverMP, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name relaxes $p3 body and recover $p3 energy"), null, new MsgBasicCD());
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
			customTime = customExecutionTime;
			customExecutionTime = 0;
			super.load();
		}
		
		@Override
		public boolean isCustomTime() {
			// TODO Auto-generated method stub
			return true;
		}
		
		@Override
		public void execute(Character target, float time) {
			//System.out.println("" + -getCharacter().str*0.7f);
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Meditate", getCharacter().getMpRegen()*3*(prof + 1), customTime, Buff.Type.mpRegen, GenType.neutral));
			getCharacter().applyBuff(new Buff("Meditate Debuff", -getCharacter().getInt()*0.7f*(1 - prof ) , customTime, Buff.Type.inte, GenType.negative));
			super.execute(target, time);
		}

	}
}