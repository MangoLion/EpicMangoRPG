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
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillStormSand extends Skill {

	public SkillStormSand() {
		super("Sand Storm", "Summon a giant tornado that blow away enemies",Weapons.Wand, ActionType.Magic);
		weapons.add(Weapons.Staff);
		addSteps(new Step(this, "Sand Storm", "",ActionType.Magic, 1f, 0.3f, 0.1f, 0.7f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 10;
			isAOE = true;
			setElement(new Element("Earth", 1));
			setEvents(new EventStorm(0.3f, getCharacter(), null, 20, this));
				super.init();
			}

		}.setChances(1, 0, -0.2f),
		new Step(this, "Sand Storm", "",ActionType.Magic, 0.5f, 0.3f, 0.1f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 15;
			isAOE = true;
			setEvents(new EventStorm(0.3f, getCharacter(), null, 20, this));
			setElement(new Element("Earth", 1));
			
				super.init();
			}
	
		}.setChances(1, 0, -0.2f),
		new Step(this, "Sand Storm", "",ActionType.Magic, 0.5f, 0.3f, 0.1f, 1.2f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 20;
			isAOE = true;
			setEvents(new EventStorm(0.3f, getCharacter(), null, 20, this));
			setElement(new Element("Earth", 1));
			
				super.init();
			}

		}.setChances(1, 0, -0.2f));
		setObservable(true, 0.7f);
	}
	public class EventStorm extends EventRange{

		public EventStorm(float time, Character source, Character target, float dmgbase, Step step) {
			super("Sand Storm", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, null);
			isAOE = true;
			//setStatus(new StatusPoison(null, 1), 1);
		}
		
		@Override
		public void execute() {
			target.applyBuff(new Buff("Blind", 2, GenType.negative, Buff.Type.accuracy, Buff.Type.crit).setValue(-0.2f, - 0.2f));
			super.execute();
		}

}
}
