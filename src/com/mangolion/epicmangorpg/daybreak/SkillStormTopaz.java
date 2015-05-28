package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.StatusFall;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillStormTopaz extends Skill {

	public SkillStormTopaz() {
		super("Topaz Storm", "Summon numerous topaz bolts and guide them towards the enemy. Requires the user to concentrate to control the bolts, very high accuracy. Element Wind. Very small chance of making the target fall.",Weapons.ALL, ActionType.Magic);
		weapons.add(Weapons.Staff);
		addSteps(new Step(this, "Topaz Storm", "",ActionType.Magic, 0.7f, 0.7f, 0f, 0.7f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 45;
			setElement(new Element("Wind", 1));
			setStatus(new StatusFall(null, 0.1f), 0.2f);
			for (int i = 0; i < 6; i ++)
				setEvents(new EventStorm(0.6f, getCharacter(), null, 20, this));
			setChances(1, 1, -0.5f);
				super.init();
			}
		}
			);
		setObservable(true, 0.7f);
	}
	public class EventStorm extends EventRange{

		public EventStorm(float time, Character source, Character target, float dmgbase, Step step) {
			super("Topaz Bolt", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, null);
			isAOE = true;
			//setStatus(new StatusPoison(null, 1), 1);
		}

}

}
