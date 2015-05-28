package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.StatusFall;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillCycloneBlast extends Skill {

	public SkillCycloneBlast() {
		super("Cyclone Blast", "Summon a giant tornado that blow away enemies, coupled with deadly runes that explode on touch, has elements of both wind and bomb. Hit 3 times",Weapons.ALL, ActionType.Magic);
		weapons.add(Weapons.ALL);
		addSteps(new Step(this, "Cyclone Blast", "",ActionType.Magic, 1f, 0.3f, 0f, 0.5f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 15;
			mpCost = 35;
			isAOE = true;
			setElement(new Element("Wind", 0.5f));
			setElement(new Element("Bomb", 0.5f));
			setEvents(new EventStorm(0.3f, getCharacter(), null, 20, this),new EventStorm(0.5f, getCharacter(), null, 20, this),new EventStorm(0.7f, getCharacter(), null, 20, this));
			setMessages(null, new Msg("A gush of hurricane-force winds threatens to blow away $name's enemies, the blast of air also acted as a contingency trigger, pulling dozens of runic pebbles into the air, hurling about the enclosed room like a whirlwind of destruction, exploding each time one of them met a living entity that did not carry the ether of their creator "), null);
			setStatus(new StatusFall(null, 0.6f), 1);
				super.init();
			}
					
		}.setChances(1, 0, 0f));
		setObservable(true, 0.7f);
	}
	public class EventStorm extends EventRange{

		public EventStorm(float time, Character source, Character target, float dmgbase, Step step) {
			super("Cyclone Blast", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, null);
			isAOE = true;
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
