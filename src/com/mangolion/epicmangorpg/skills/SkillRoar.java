package com.mangolion.epicmangorpg.skills;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.steps.StepMeleeSlash;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillRoar extends Skill {
	Random rand = new Random();


	public SkillRoar() {
		super("Roar", "Instill fear into the enemy's heart, stunning and intimidate them and give courage buff to all allies",Weapons.ALL, ActionType.MeleeSpecial);
		hasTarget = false;
		addSteps(new StepMeleeSlash(this, "Roar","", 0.5f,
				0.6f, 0.2f, 1f) {
			
			public void execute(Character target, float time, String aug) {
				for (Character c: Game.getInstance().getAllies(getCharacter()))
					c.applyBuff(new Buff("Courage", 2, GenType.positive, Buff.Type.str, Buff.Type.agi, Buff.Type.inte).setValue((1+prof)*0.3f, (1+prof)*0.3f, (1+prof)*0.3f));
				super.execute(target, time, aug);
			};
			
		}.setAOE(true).setCost(40, 0, 15, 0)
		 .setChances(1, 0, 0.2f)
		 .setBuff(new Buff("Intimidate", 2, GenType.negative, Buff.Type.str, Buff.Type.agi, Buff.Type.inte).setValue(-(1+prof)*0.3f,- (1+prof)*0.3f, -(1+prof)*0.3f)));
		setObservable(true, 1);
	}
	
}
