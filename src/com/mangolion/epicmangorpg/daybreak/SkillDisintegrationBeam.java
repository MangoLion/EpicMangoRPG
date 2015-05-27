package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillDisintegrationBeam extends Skill {

	public SkillDisintegrationBeam() {
		super("Disintegration Beam", "Common anti-air measure of the mantis assasination team. Dark element, while this skill is considered to be ranged, it hits (or misses) instantly.",Weapons.ALL, ActionType.Magic) ;
		setObservable(true, 0.7f);
		shopPrice = 60;
		addSteps(new Step(this, "Disintegration Beam", "", ActionType.Magic, 0.6f, 0.1f, 0.1f,  2f){

			public void init() {
				intBased = true;
				doDamage = true;
				setCost(10, 25, 0, 0);
				setMessages(new Msg("Three translucent black rings of magical energy formed the firing barrel as $name charged up a pulsing multi-hitter Disintegration Beam."), null, null);
			};

			public float getIntBuff() {
				return prof*10;
			};

		}.setElement(new Element("Dark", 1)));
	}
	
}
