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
					c.applyBuff(new Buff("Courage", 2, GenType.positive, Buff.Type.str, Buff.Type.agi, Buff.Type.inte).setValues(getCharacter().getStr()*(1+prof)*0.3f, getCharacter().getAgi()*(1+prof)*0.3f, getCharacter().getInt()*(1+prof)*0.3f));
				super.execute(target, time, aug);
			};
			
			public boolean damage(Character target, boolean checkMiss) {

				
				float miss = ( chanceMiss) + (1 - getCharacter().getAccuracy(target));
				if (getCharacter().weapon.isAutomatic)
					miss *= 1.2f;
				System.out.println("miss: " + miss);
				if (rand.nextFloat() <= miss && !isAOE && checkMiss) {
					StylePainter.append(new MsgSlashMiss().getMessage(getCharacter(),
							target, 0));
					if (target.skillCurrent != null && target.skillCurrent.type == ActionType.Dodge){
						
						target.skillCurrent.steps.get(target.skillCurrent.stepCurrent).addProf(new Proficiency(target, getCharacter()));
					}
					return false;
				}
				
				float eleMult = -2;
				for (Element element: target.getElements()){
					if (this.element != null)
						eleMult = (eleMult == -2)? Elements.getElement(element.type).calculate(this.element.type): (eleMult +  Elements.getElement(element.type).calculate(this.element.type))/2;
						else if (getCharacter().getElements().size() > 0)
							eleMult = (eleMult == -2)?  Elements.getElement(element.type).calculate(getCharacter().getElements().getFirst().type): (eleMult +  Elements.getElement(element.type).calculate(getCharacter().getElements().getFirst().type))/2;
				}
				//System.out.println(getCharacter().name + " " + eleMult);
				if (eleMult == -2)
					eleMult = 1;
				float dmg;
				
					for (Character character: Game.getInstance().getEnemies(getCharacter())){
						if (character.isAirborne()){
							StylePainter.append(new Msg("$name cannot reach $targetname because $p is airborne!").getMessage(getCharacter(), target, 0));
							continue;
						}
						boolean excepted = false;
						if (calculateChanceMelee(character))
							continue;
						for (Character character2 : aoeExceptions){
							System.out.println(character.name + " " + character2.name); 
						if (character == character2)
							excepted = true;
						}
						if (excepted)
							continue;
						dmg = getDamage()*eleMult;
						float crit = getCharacter().getCritical(target) + critBase;
						System.out.println("crit: " + crit);
						if (rand.nextFloat() <= crit) {
							StylePainter.append(new Msg("$name has landed a critical!").getMessage(getCharacter(), null, 0));
							dmg *= 1.5;
						}
						character.setDamage(getCharacter(), dmg);
						character.applyBuff(new Buff("Intimidate", 2, GenType.negative, Buff.Type.str, Buff.Type.agi, Buff.Type.inte).setValues(-getCharacter().getStr()*(1+prof)*0.3f, -getCharacter().getAgi()*(1+prof)*0.3f, -getCharacter().getInt()*(1+prof)*0.3f));
					}
				aoeExceptions.clear();
					addProf(new Proficiency(getCharacter(), target));
					subtractDamage = 0;
					return true;
			};
		}.setAOE(true).setCost(40, 0, 15, 0)
		 .setChances(1, 0, 0.2f));
		setObservable(true, 1);
	}
	
}
