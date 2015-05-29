package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillStormFire extends Skill {

	public SkillStormFire() {
		super("Fire Storm", "Summon a giant fire storm that burns all enemies, hit 3 times",Weapons.Wand, ActionType.Magic);
		weapons.add(Weapons.Staff);
		addSteps(new Step(this, "Fire Storm", "",ActionType.Magic, 1f, 0.3f, 0.1f, 0.7f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 10;
			isAOE = true;
			setElement(new Element("Fire", 1));
			setEvents(new EventFireStorm(0.3f, getCharacter(), null, 20, this));
				super.init();
			}
	
		}.setChances(1, 0, -0.2f),
		new Step(this, "Fire Storm", "",ActionType.Magic, 0.5f, 0.3f, 0.1f, 1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 15;
			isAOE = true;
			setElement(new Element("Fire", 1));
			setEvents(new EventFireStorm(0.4f, getCharacter(), null, 20, this));
				super.init();
			}
	
		}.setChances(1, 0, -0.2f),
		new Step(this, "Fire Storm", "",ActionType.Magic, 0.5f, 0.3f, 0.1f, 1.2f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 20;
			isAOE = true;
			setEvents(new EventFireStorm(0.5f, getCharacter(), null, 20, this));
			setElement(new Element("Fire", 1));
				super.init();
			}

		}.setChances(1, 0, -0.2f));
		setObservable(true, 0.7f);
	}
	public class EventFireStorm extends EventRange{

		public EventFireStorm(float time, Character source, Character target, float dmgbase, Step step) {
			super("Fire Storm", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, null);
			isAOE = true;
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
