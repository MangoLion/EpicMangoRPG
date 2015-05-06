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
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class RabbitGiant extends Character{

	public RabbitGiant() {
		super("Giant Rabbit", "The first and possibly easiest boss you'll ever have.",150, 0, 200, 70, 10, 100, 10, 10, 0, 0,new Weapon("Paws", 8, 100, Weapons.BareHand, 1, 1, 1f),   new SkillBlock(), new SkillDodge(), new SkillJumpAtk(), new SkillSlashBasic(), new SkillBite());
		addElements(new Element("Furry", 1));
		ai = new AISimple(this);
		
		addDrop(Items.rabbitHide, 1, 4);
		addDrop(Items.mstoneLarge, 0.8f, 1);
	}
}
