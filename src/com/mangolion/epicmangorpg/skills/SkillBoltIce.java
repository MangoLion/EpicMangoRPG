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

public class SkillBoltIce extends Skill {

	public SkillBoltIce() {
		super("IceBolt", "Condesing the water vapor from the air and freeze them into a large chunk of ice crystals",Weapons.ALL, ActionType.Magic);
		addSteps(new Step(this, "IceBolt", "",ActionType.Magic, 0.3f, 0.1f, 0.1f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 10;
			setElement(new Element("Ice", 1));
			setEvents(new EventIceBolt(0.3f, getCharacter(), null, 20, this));
				super.init();
			}
		});
		setObservable(true, 0.7f);
	}
	public class EventIceBolt extends EventRange{

		public EventIceBolt(float time, Character source, Character target, float dmgbase, Step step) {
			super("Icebolt", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
