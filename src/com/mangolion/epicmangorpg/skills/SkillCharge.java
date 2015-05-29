package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockExecute;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockLoad;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeKick;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillCharge extends Skill {
	
	
	public SkillCharge() {
		super("Charge", "Raise your weapon defensively and charges towards the target, you will block most attacks while the skill is activated, and slam into the target, stunning them",Weapons.ALL, ActionType.MeleeBlock);
		shopPrice = 30;
		addSteps(new Step(this, "Charge Run", "",ActionType.MeleeBlock, 0.5f, 0.5f, 0f,0) {
public void execute(Character target, float time) {
	super.execute(target, time);
	value = (float) (50 + character.getMaxHP()*0.1)*(1 + prof);
};
	public float getHPBuff() {
		return prof * 10;
	};
	
	public float getStrBuff() {
		return prof*5;
	};
	
	public float getBalBuff() {
		return prof*10;
	};
	}.setCost(20, 0, 0, 0),
	new StepMeleeKick(this, "Charge Bash", "", 0f, 0.2f, 0.6f, 15, 1f) {
		@Override
		public float getStrBuff() {
			// TODO Auto-generated method stub
			return prof * 20;
		}
	}.setStatus(new StatusStun(null, 0.7f), 0.8f)
	.setCost(10, 0, 40, 0));
	}
	
	@Override
	public boolean checkCompatability(Character target) {
		// TODO Auto-generated method stub
		return !target.isAirborne();
	}
}
