package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBasicHeal;
import com.mangolion.epicmangorpg.skills.SkillBasicKick;
import com.mangolion.epicmangorpg.skills.SkillBasicSlash;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBite;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillJumpAtk;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillWebFiring;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.WeaponGreatCopperSword;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;
import com.mangolion.epicmangorpg.weapons.WeaponSpiderFang;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class FangSpider extends Character{
	SkillBite bite = new SkillBite();
	public FangSpider() {
		super("Fang Spider", "Scary  spider with poison fangs.",70, 0, 100, 40, 10, 90, 10, 10, 0, 0,new WeaponSpiderFang(),   new SkillBlock(), new SkillDodge(), new SkillJumpAtk(), new SkillWebFiring());
		ai = new AISimple(this);
		bite.steps.getFirst().setStatus(new StatusPoison(null, 1), 1f);
		addSkills(bite);
		cpBase = 100;
	}

}
