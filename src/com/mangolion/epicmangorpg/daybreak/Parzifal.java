package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillJumpBack;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.weapons.LongSword;

public class Parzifal extends Character {

	public Parzifal() {
		super("Parzifal", "Ariadne's lover, he has short brown hair above pretty aquamarine eyes, a wide nose, and strong jaws. He had a lean musculature that emphasized powerful legs in particular, yet his steps were soft, almost silent."
				, 80, 120, 100, 30, 20, 150, 60, 20, 0, 0,
				new LongSword(), new SkillBlock(),
				new SkillKickBasic(), new SkillParry(),new SkillSlashBasic(), new SkillSideStep(), new SkillJumpBack(),
				new SkillHealBasic(), new SkillFlourishingBrambles(), new SkillWait());
		ai = new AISimple(this);
		isAllied = true;
	}

}
