package com.mangolion.epicmangorpg.skills;



import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.StatusFall;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillArmorWind extends Skill {

	public SkillArmorWind() {
		super("Wind Armor", "Summons a thick wind blanket, covering all allies, increasing their defense and agility by 30%, protection by 15% for 10 seconds.",Weapons.ALL, ActionType.Magic);
		addSteps(new Step(this, "Wind Armor", "",ActionType.Magic, 1f, 0.1f, 0.1f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 10;
			mpCost = 10;
			super.init();
			}
			@Override
			public void execute(Character target) {
				for (Character c: Game.getInstance().getAllies(getCharacter()))
					c.applyBuff(new Buff("Wind Armor", 10, GenType.positive, Buff.Type.agi, Buff.Type.def, Buff.Type.prot).setValue(0.3f, 0.3f, 0.15f).setElement(new Element("Wind", 0.3f)));
				super.execute(character);
			}			
			@Override
			public float getIntBuff() {
				// TODO Auto-generated method stub
				return prof*10;
			}
		});
		setObservable(true, 0.7f);
	}
	
}
