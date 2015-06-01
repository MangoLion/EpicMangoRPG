package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockExecute;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillJamFix extends Skill {

	public SkillJamFix() {
		super("Jam Fix", "", Weapons.Reloadable, ActionType.RecoverJam);
		addSteps(new Step(this, "Jam Fix", "", ActionType.RecoverJam, 0f, 0.1f,
				0f, 0) {
			
			public void init() {
				value = 0.9f;
			};
			public void execute(Character target, float time) {
				super.execute(target, time);
				if (rand.nextFloat() <= value){
					getCharacter().weapon.isJammed = false;
					StylePainter.append(new Msg("$name's $weapon is no longer jammed").getMessage(getCharacter(), null, 0));
				}else
					StylePainter.append(new Msg("$name has failed to unjam $p3 $weapon").getMessage(getCharacter(), null, 0));
					
			};

			public boolean checkConndition() {
				if (getCharacter().weapon.isJammed)
					return super.checkConndition();
				else
					Utility.narrate(getCharacter().name + "'s weapon is not jammed.");
				return false;
			};

			public float getLoadTime() {
				return getCharacter().weapon.reloadTime*1.2f;
			};
		}.setCost(15, 0, 0, 0));
	}
}
