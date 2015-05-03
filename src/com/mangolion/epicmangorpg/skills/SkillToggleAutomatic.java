package com.mangolion.epicmangorpg.skills;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillToggleAutomatic extends Skill {

	public SkillToggleAutomatic() {
		super("Toggle Automatic", "", Weapons.Reloadable, ActionType.RecoverAmmo);
		hasTarget = false;
		addSteps(new Step(this, "Toggle Automatic", "", ActionType.RecoverAmmo, 0.2f, 0.1f,
				0f, 0) {
			public void execute(Character target, float time) {
				super.execute(target, time);
				getCharacter().weapon.isAutomatic = !getCharacter().weapon.isAutomatic; 
			};
			public boolean checkConndition() {
				if (!getCharacter().weapon.canToggleAutomatic){
					Utility.narrate(getCharacter().name + "'s weapon cannot be toggled.");
					return false;
				}
				else
					return super.checkConndition();
			};
		}.setCost(8, 0, 0, 0));
	}
}
