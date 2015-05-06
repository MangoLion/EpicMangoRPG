package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillClawSnap;
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
import com.mangolion.epicmangorpg.skills.SkillSting;
import com.mangolion.epicmangorpg.skills.SkillTailSwing;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class ScropionWolf extends Character{

	public ScropionWolf() {
		super("Wolf Scorpion", "A cow sized scorpion with very tough scales and as agile as a wolf.", 200, 0, 250, 70, 50, 100, 10, 10, 10, 5,new Weapon("Claws", 15, 100, Weapons.Sword, 1, 1, 1f),  new SkillBlock(), new SkillClawSnap(),new SkillTailSwing(), new SkillSting());
		ai = new AISimple(this);
		addElements(new Element("Tough Scales", 1));
		
		addDrop(Items.hardScale, 0.7f, 2);
		addDrop(Items.mstone, 1f, 1);
	}
}
