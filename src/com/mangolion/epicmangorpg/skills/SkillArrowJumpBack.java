package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepDodge;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArrowJumpBack extends Skill{

	public SkillArrowJumpBack() {
		super("Jump Back Arrow", "Jumping backwards to reduce 25% of the damage taken, while loosing an arrow at point blank.",Weapons.Bow, ActionType.Defend);
		setObservable(true, 0.7f);
		shopPrice = 40;
		addSteps(new Step(this, "Jump Back Arrow", "",  ActionType.Defend, 0.2f, 0.3f, 0.4f, 1.2f){
			
			public void init() {
				setEvents(new EventArrow(0.3f, getCharacter(), null, 20, this));
			};
			
			@Override
			public float getAgiBuff() {
				// TODO Auto-generated method stub
				return prof*25;
			}
			
			public boolean checkConndition() {
				if (getCharacter().inventory.getItemNumber(Items.arrow) > 0)
					return super.checkConndition();
				return false;
			};
			
			@Override
			public float getSPBuff() {
				// TODO Auto-generated method stub
				return prof*15;
			}
			
			@Override
			public void execute(Character target, float time) {
				addProf(new Proficiency(getCharacter(), getCharacter()));
				getCharacter().applyBuff(new Buff("Jump Back Boost", 25, 0.3f, GenType.positive, Buff.Type.prot));
				getCharacter().inventory.removeItem(Items.arrow, 1);
				super.execute(target, time);
			}
		}.setCost(30, 0, 40, 0));
	}
}
