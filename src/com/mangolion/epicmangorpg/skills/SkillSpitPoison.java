package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.events.EventWebFire;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSpitPoison extends Skill {

	public SkillSpitPoison() {
		super("Spit Poison", "Cannot be parried",Weapons.ALL, ActionType.RangeNormal);
		addSteps(new Step(this, "Spit Poison", "",ActionType.RangeNormal, 0.3f, 0.2f, 0.2f, 1){
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventSpitPoison(0.5f, getCharacter(), target, 20, this));
				setStatus(new StatusPoison(null, 1), 1);
				super.execute(character);
			}			
		}.setCost(15, 15, 5, 0));
	}
	public class EventSpitPoison extends EventRange{

		public EventSpitPoison(float time, Character source, Character target, float dmgbase, Step step) {
			super("Poison Spit", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
		}

	}

}
