package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.ais.AISummon;
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
import com.mangolion.epicmangorpg.skills.SkillSpitPoison;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillSummon;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.WeaponGreatCopperSword;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SoldierAnt extends Character{

	public SoldierAnt() {
		super("Soldier Ant", "A man sized ant with tough armor for skin, if you don't finish it fast, it'll call for reinforcements", 100, 0, 250, 50, 20, 100, 10, 10, 10, 5,new Weapon("Claws", 10, 100, Weapons.Sword, 1, 1, 0.8f),  new SkillBite(), new SkillParry(), new SkillSummon(SoldierAnt.class, 0.5f));
		ai = new AISummon(this);
	}
}
