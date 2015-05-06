package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
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
import com.mangolion.epicmangorpg.skills.SkillSideJump;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class FoxFang extends Character{

	public FoxFang() {
		super("Fanged Fox", "A slightly more dangerous looking fox with much sharper teeth that are poisoned.",65, 70, 80, 30, 15, 80, 30, 10, 0, 0,new Weapon("Claws", 0, 100, Weapons.Claws, 1, 1, 1f),  new SkillSideJump(), new SkillSlashBasic());
		addElements(new Element("Furry", 1));
		ai = new AISimple(this);
		SkillBite bite = new SkillBite();
		bite.steps.getFirst().setStatus(new StatusPoison(null, 1), 1f);
		addSkills(bite);
		cpBase = 80;
		
		addDrop(Items.foxHide, 0.7f, 1);
		addDrop(Items.mstoneSmall, 0.4f, 1);
	}
}