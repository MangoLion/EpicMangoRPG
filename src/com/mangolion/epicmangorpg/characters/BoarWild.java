package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBarrelRoll;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class BoarWild extends Character{

	public BoarWild() {
		super("Wild Boar", "A tough boar with sharpp  tusks, it can easily ram through the average adventure.", 90, 80, 100, 50, 15,60, 10, 10, 8, 3,new Weapon("Tusks", 0, 100, Weapons.BareHand, 1, 1, 1),   new SkillBlock(), new SkillParry(), new SkillBodySlam());
		ai = new AISimple(this);
		addElements(new Element("Tough Hide", 1));
		
		addDrop(Items.boarHide, 0.7f, 1);
		addDrop(Items.mstone, 0.4f, 1);
	}

}
