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

public class SkillBoltRock extends Skill {

	public SkillBoltRock() {
		super("Cast Rock", "",Weapons.ALL, ActionType.Magic);
		addSteps(new Step(this, "Cast Rock", "Create and launch a small cluster of rocks.",ActionType.Magic, 0.3f, 0.1f, 0.1f, 1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 10;
			setEvents(new EventCastRock(0.3f, getCharacter(), null, 20, this));
			setElement(new Element("Earth", 1));
				super.init();
			}
	
			@Override
			public float getIntBuff() {
				// TODO Auto-generated method stub
				return prof*10;
			}
		});
		setObservable(true, 0.7f);
	}
	public class EventCastRock extends EventRange{

		public EventCastRock(float time, Character source, Character target, float dmgbase, Step step) {
			super("Rock", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
