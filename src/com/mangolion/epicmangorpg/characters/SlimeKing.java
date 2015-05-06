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

public class SlimeKing extends Character{

	public SlimeKing() {
		super("King Slime", "A giant blob of yellow slime, its much more dangerous than your average slime. ", 200, 0, 600, 60, 10, 300, 10, 10, 10, 10,new Weapon("Slimes", 7, 100, Weapons.BareHand, 1F, 1, 1),   new SkillBlock(), new SkillBodySlam(), new SkillParry(), new SkillSlashHeavy(), new SkillStab());
		addElements(new Element("Plant", 1));
		ai = new AISimple(this);
		
		addDrop(Items.slime, 1f, 5);
		addDrop(Items.mstoneLarge, 1f, 1);
	}
}
