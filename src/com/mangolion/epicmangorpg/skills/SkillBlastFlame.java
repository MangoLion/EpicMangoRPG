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
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBlastFlame extends Skill {

	public SkillBlastFlame() {
		super("Flame Burst", "",Weapons.Cylinder, ActionType.Alchemy);
		shopPrice = 20;
		setObservable(true, 0.7f);
		addSteps(new StepMeleeSlash(this, "Flame Burst", "", 0.5f, 0.1f, 0.1f,1f){
			@Override
			public void init() {
			type = ActionType.Alchemy;
			strBased = false;
			dmgBase = 10;
			isCharged = true;
			chanceParry = 0;
			isAOE = true;
			timeChargeExecute = 0.4f;
			setElement(new Element("Fire", 1));
				super.init();
			}

		}.setCost(20, 0, 0, 0).setUseItem(Items.crystalFire, 1));
		setObservable(true, 0.7f);
	}
}
