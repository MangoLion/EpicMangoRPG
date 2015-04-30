package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSlashBasic extends Skill {
	Random rand = new Random();


	public SkillSlashBasic() {
		super("Slash", "Basic  slash, fast, but weak. fast loading speed, works with most weapons.",Weapons.ALL, ActionType.MeleeSwing);
		addSteps(new StepMeleeSlash(this, "Slash","", 0.5f,
				0.2f, 0.4f,1f) {
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
		}.setCost(15, 0, 3, 0));
	}
	
}
