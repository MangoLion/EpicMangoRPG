package com.mangolion.epicmangorpg.daybreak;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.statuses.StatusStun;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillKyun extends Skill {
	Random rand = new Random();


	public SkillKyun() {
		super("Kyun", "Overwhelm the battle field with cuteness, stunning ALL characters! Give courage buff to all allies (except the user)  and severly debuff all enemies. User is inflicted with embarrased status, last for 10 seconds",Weapons.ALL, ActionType.Buff);
		hasTarget = false;
		addSteps(new Step(this, "Kyun","", ActionType.Buff, 0.5f,
				0.6f, 0.2f, 1f) {

			
			public boolean checkConndition() {
			
				for (Buff buff: getCharacter().buffs)
					if (buff.name.equals("Embarrased")){
						if (getCharacter() == CharacterPlayer.instance)
							Utility.narrate("You can only have one buff of the same type");
						return false;
					}
				return super.checkConndition();
			};
			
			public void execute(Character target, float time, String aug) {
				StylePainter.append(new Msg("Everyone on the battlefield is stunned as $name bathes the battlefield in $p3 cuteness!").getMessage(getCharacter(), null, 0));
				
				for (Character character: Game.getInstance().getAllChars())
					character.addStatus(new StatusStun(character, 0.1f));
				
				getCharacter().applyBuff(new Buff("Embarrased", 10, GenType.negative, Buff.Type.accuracy, Buff.Type.agi, Buff.Type.inte).setValue(-0.2f,- (1+prof)*0.2f, -(1+prof)*0.2f));
				for (Character c: Game.getInstance().getAllies(getCharacter()))
					if (c != getCharacter())
						c.applyBuff(new Buff("Courage", 10, GenType.positive, Buff.Type.str, Buff.Type.agi, Buff.Type.inte).setValue((1+prof)*0.3f, (1+prof)*0.3f, (1+prof)*0.3f));
				for (Character c: Game.getInstance().getEnemies(getCharacter()))
					if (c != getCharacter())
						c.applyBuff(new Buff("Dispirited",10, GenType.negative, Buff.Type.str, Buff.Type.agi, Buff.Type.inte).setValue(-(1+prof)*0.3f,- (1+prof)*0.3f, -(1+prof)*0.3f));
				super.execute(target, time, aug);
			};
			
		}.setAOE(true).setCost(20, 20, 15, 0)
		 .setChances(1, 0, 0.2f));
		setObservable(true, 1);
	}
	
}
