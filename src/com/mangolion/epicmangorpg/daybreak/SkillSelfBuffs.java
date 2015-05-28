package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Utility;
public class SkillSelfBuffs extends Skill{

	public SkillSelfBuffs() {
		super("Self Buffs", "Activates 4 rune based buffs, Pascal signature skill", ActionType.Buff, Weapons.ALL);
		hasTarget = false;
		addSteps(new Step(this, "Self Buffs", "", ActionType.Buff,0.5f, 0.1f, 0.0f, 0) {
			public boolean checkConndition() {
				for (Buff buff: character.buffs)
					if (buff.name.equalsIgnoreCase("Sensory Clarity")){
						if (getCharacter() == CharacterPlayer.instance)
							Utility.narrate("You can only have one buff of the same type");
						return false;
					}
				return super.checkConndition();
			};
			@Override
			public void execute(Character target, float time, String aug)  {
				getCharacter().applyBuff(new Buff("Sensory Clarity", 10, GenType.positive, Buff.Type.dex, Buff.Type.spCost, Buff.Type.mpCost, Buff.Type.balCost ).setValue( (prof + 1)*0.5f,0.2f*(1 - prof ),0.2f*(1 - prof ),0.2f*(1 - prof )));
				getCharacter().applyBuff(new Buff("Shift Impulse", 10, GenType.positive, Buff.Type.agi, Buff.Type.spCost, Buff.Type.mpCost, Buff.Type.balCost ).setValue( (prof + 1)*0.5f,0.2f*(1 - prof ),0.2f*(1 - prof ),0.2f*(1 - prof )));
				getCharacter().applyBuff(new Buff("Metabolic Boost", 10, GenType.positive, Buff.Type.spRegen, Buff.Type.spCost, Buff.Type.mpCost, Buff.Type.balCost ).setValue( (prof + 1)*0.25f,0.05f*(1 - prof ),0.05f*(1 - prof ),0.05f*(1 - prof )));
				getCharacter().applyBuff(new Buff("Elemental Body of Earth", 10, Buff.GenType.positive, Buff.Type.str).setValue(0.2f).setElement(new Element("Earth", 1)));
			super.execute(target,time,aug);
			}
		}.setCost(0, 0, 0, 0)
		.setMessages(new Msg("$name activates four buffs of $p3 usual defensive array"), new Msg("$name has activated 4 buffs, Sensory Clarity, Shift Impulse, Metabolic Boost and Elemental Body of Earth"), null));
	}

}
