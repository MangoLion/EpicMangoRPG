package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockExecute;
import com.mangolion.epicmangorpg.messages.MsgMeleeBlockLoad;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillReload extends Skill {

	public SkillReload() {
		super("Reload", "", Weapons.Reloadable, ActionType.RecoverAmmo);
		hasTarget = false;
		addSteps(new Step(this, "Reload", "", ActionType.RecoverAmmo, 0f, 0.1f,
				0f, 0) {
			public void execute(Character target, float time) {
				super.execute(target, time);
				getCharacter().weapon.ammo = getCharacter().weapon.maxAmmo;
				getCharacter().inventory.removeItem(Items.bullet,
						getCharacter().weapon.maxAmmo);
			};

			public boolean checkConndition() {
				if (getCharacter().inventory.getItemNumber(Items.bullet) > getCharacter().weapon.maxAmmo)
					return super.checkConndition();

				return false;
			};

			public float getLoadTime() {
				return getCharacter().weapon.reloadTime;
			};
		}.setCost(15, 0, 0, 0));
	}
}
