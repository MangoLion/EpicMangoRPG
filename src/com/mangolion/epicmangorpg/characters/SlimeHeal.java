package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AIHealer;
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

public class SlimeHeal extends Character{

	public SlimeHeal() {
		super("Heal Slime", "A small and cute pink slime, it only knows how to heal its allies.",40, 0, 40, 30, 10, 30, 10, 10, 0, 0,new Weapon("Slimes", 0, 100, Weapons.BareHand, 1, 1, 1),   new SkillBlock(), new SkillDodge(), new SkillHealBasic());
		ai = new AIHealer(this);
		addElements(new Element("Plant", 1));
		isSupporter = true;
		cpBase = 50;
		
		addDrop(Items.slime, 08f, 1);
		addDrop(Items.mstoneSmall, 0.6f, 1);
	}

}
