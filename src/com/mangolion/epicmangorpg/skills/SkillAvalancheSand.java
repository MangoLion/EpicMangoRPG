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

public class SkillAvalancheSand extends Skill {

	public SkillAvalancheSand() {
		super("Sand Avalanche", "Cause sand everywhere to pour down and sweep targets of their feet, cannot be blocked, dodged or parried.",Weapons.ALL, ActionType.MeleeSpecial);
		hasTarget = false;
		addSteps(new Step(this, "Sand Avalanche", "",ActionType.MeleeSpecial, 0.8f, 0.3f, 0.1f,1f){
			@Override
			public void init() {
			mpCost = 30;
			stamCost = 20;
				
			strBased = false;
			intBased = true;
			dmgBase = 35;
			isAOE = true;
			setElement(new Element("earth", 1));
				super.init();
			}
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventAvalanche(1, getCharacter(), target, 20, this));
				super.execute(character);
			}			
		});
		setObservable(true, 0.7f);
	}
	public class EventAvalanche extends EventRange{

		public EventAvalanche(float time, Character source, Character target, float dmgbase, Step step) {
			super("Sand Avalanche", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
