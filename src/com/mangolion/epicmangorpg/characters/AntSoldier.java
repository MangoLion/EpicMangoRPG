package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.ais.AISummon;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
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
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class AntSoldier extends Character{

	public AntSoldier() {
		super("Soldier Ant", "A man sized ant with tough armor for skin, if you don't finish it fast, it'll call for reinforcements", 100, 100, 250, 50, 20, 100, 10, 10, 10, 5,new Weapon("Claws", 10, 100, Weapons.Sword, 1, 1, 1f),  new SkillBite(), new SkillParry(), new SkillSummon(AntSoldier.class, 0.5f));
		ai = new AISummon(this);
		addElements(new Element("Tough Scales", 1));
		
		addDrop(Items.antShell, 0.7f, 1);
		addDrop(Items.mstone, 0.6f, 1);
	}
}
