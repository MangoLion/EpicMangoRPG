package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillLaunchGrenade extends Skill {

	public SkillLaunchGrenade() {
		super("Launch Grenade", "",Weapons.LauncherGrenade, ActionType.RangeNormal);
		addSteps(new Step(this, "Launch Grenade", "",ActionType.RangeNormal, 0.5f, 0.1f, 0.1f,1f){
			@Override
			public void init() {
			dmgBase = 10;
			chanceParry = 0;
			doDamage = false;
			isAOE = true;
			setElement(new Element("Bomb", 1));
			setEvents(new Launch(0.5f, getCharacter(), null, 20, this));
				super.init();
			}

		}.setCost(20, 0, 0, 0).setUseItem(Items.grenadeAmmo, 1));
		setObservable(true, 0.7f);
	}
	public class Launch extends EventRange{

		public Launch(float time, Character source, Character target, float dmgbase, Step step) {
			super("Launch Grenade", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
