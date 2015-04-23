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
import com.mangolion.epicmangorpg.skills.SkillFireBolt;
import com.mangolion.epicmangorpg.skills.SkillIceBolt;
import com.mangolion.epicmangorpg.skills.SkillJumpAtk;
import com.mangolion.epicmangorpg.skills.SkillLightningBolt;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.WeaponGreatCopperSword;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class LightningSprite extends Character{

	public LightningSprite() {
		super("Lighting Sprite", "A floating ball of lightning with wings, mainly use lightning skills.",70, 70, 40, 40, 10, 100, 60, 10, 0, 0,new Weapon("Lightning", 0, 100, Weapons.BareHand, 1, 1, 0.8f),  new SkillBodySlam(), new SkillBlock(), new SkillBasicSlash(), new SkillLightningBolt());
		ai = new AISimple(this);
	}
}
