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
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBlastSand extends Skill {

	public SkillBlastSand() {
		super("Sand Blast", "",Weapons.Cylinder, ActionType.Alchemy);
		shopPrice = 20;
		setObservable(true, 0.7f);
		addSteps(new Step(this, "Sand Blast", "",ActionType.Alchemy, 0.4f, 0.1f, 0f,1f){
			@Override
			public void init() {
			strBased = false;
			dmgBase = 10;
			isCharged = true;
			chanceParry = 0;
			timeChargeExecute = 0.2f;
			setElement(new Element("Earth", 1));
				super.init();
			}
			public boolean damage(Character target) {
				target.applyBuff(new Buff("Blind", 0.4f, 2, GenType.negative, Buff.Type.accuracy));
				return super.damage(target);
			};
			
			@Override
			public void releaseCharge(Character target, float time) {
				Event.addEvent(new EventWindBlast(0.3f, getCharacter(), target, 20, this));
				super.releaseCharge(target, time);
			}
		}.setCost(20, 0, 0, 0).setUseItem(Items.crystalWind, 1));
		setObservable(true, 0.7f);
	}
	public class EventWindBlast extends EventRange{

		public EventWindBlast(float time, Character source, Character target, float dmgbase, Step step) {
			super("Sand", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
