package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillHardAim extends Skill {

	public SkillHardAim() {
		super("Hard Aim", "Focuses intently on the enemy while aiming, in addition to 8% accuracy increase, it also gives a 10% dex bonus for the skill duration. ",Weapons.Gun, ActionType.RangeNormal);
		addSteps(new Step(this, "Hard Aim", "",ActionType.RangeNormal, 0.8f, 0.1f, 0.15f,1){
			
			public void init() {
				useAmmo = true;
			};
			
			@Override
			public void execute(Character target) {

				for (int i = 0; i < ammoUse; i ++)
					Event.addEvent(new EventRange("Bullet", "", 0.3f, getCharacter(), target, 0, this));
				super.execute(target);
			}			
			
			public void load() {
				getCharacter().applyBuff(new Buff("Focus", getCharacter().getDex()*0.1f, getLoadTime() + getExecutionTime() + getCooldownTime(), Buff.Type.dex, GenType.positive));
				super.load();
			};
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getSPBuff() {
				return prof*15;
			};
		}.setCost(5, 0, 0, 0).setChances(1, 1, -0.08f));
	}

}