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
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBoltThorn extends Skill {

	public SkillBoltThorn() {
		super("Cast Thorn", "Pierce the enemy with poisonous thorns",Weapons.ALL, ActionType.Magic);
		addSteps(new Step(this, "Cast Thorn", "",ActionType.Magic, 0.5f, 0.1f, 0.1f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 10;
			setEvents(new EventIceBolt(0.3f, getCharacter(), null, 20, this));
			setElement(new Element("Poison", 1));
			setStatus(new StatusPoison(null, 1f), 1);
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
	public class EventIceBolt extends EventRange{

		public EventIceBolt(float time, Character source, Character target, float dmgbase, Step step) {
			super("Cast Thorn", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
		}

}
}
