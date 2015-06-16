package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSlashPrecise extends Skill {
	Random rand = new Random();


	public SkillSlashPrecise() {
		super("Precise Slash", "Take a few moments to observe enemy's movements before striking to increase accuracy and critical. Give 20% dex, 30% accuracy and critical",Weapons.ALL, ActionType.MeleeSwing);
		addSteps(new StepMeleeSlash(this, "Precise Slash","", 0.7f,
				0.2f, 0.4f,1f) {
			public float getStrBuff() {
				// TODO Auto-generated method stub
				return prof * 10;
			}
			
			public void execute(com.mangolion.epicmangorpg.characters.Character target, float time, String aug) {
				getCharacter().applyBuff(new Buff("Focus", 1, GenType.positive, Buff.Type.dex, Buff.Type.accuracy, Buff.Type.crit).setValue((prof + 1)*0.2f, (prof + 1)*0.3f,(prof + 1)*0.3f));
				super.execute(target, time, aug);
			};
		}.setCost(15, 0, 3, 0));
		setObservable(true, 1);
	}
	
}
