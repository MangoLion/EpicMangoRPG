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
		super("Hard Aim", "Focuses intently on the enemy while aiming, in addition to 10% accuracy increase, it also gives a 10% dex bonus for the skill duration. ",Weapons.Gun, ActionType.RangeNormal);
		shopPrice = 10;
		setObservable(true, 0.7f);
		addSteps(new Step(this, "Hard Aim", "",ActionType.RangeNormal, 0.8f, 0.1f, 0.15f,1){
			
			public void init() {
				useAmmo = true;
				setEvents(new EventRange("Bullet", "", 0.3f, getCharacter(), null, 0, this));
			};

			
			public void load() {
				getCharacter().applyBuff(new Buff("Focus", getCharacter().getDex()*0.1f, getLoadTime() + getExecutionTime() + getCooldownTime()+0.5f, GenType.positive,Buff.Type.dex));
				super.load();
			};
			
			public float getDexBuff() {
				return prof*20;
			};
			
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 15;
			}
			public float getSPBuff() {
				return prof*15;
			};
			
			public float getAccuracyBuff() {
				return 0.10f;
			};
		}.setCost(5, 0, 0, 0).setChances(1, 1, 0));
	}

}
