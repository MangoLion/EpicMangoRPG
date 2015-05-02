package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillBoltLightning;
import com.mangolion.epicmangorpg.skills.SkillBoltRock;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillStomp;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class GolemMetal extends Character{

	public GolemMetal() {
		super("Metal Golem", "",  80, 80, 140, 50, 5, 100, 20, 15, 10, 5, new Weapon("Metals", 5, 0, 1.2f, 1, 1.2f, Weapons.BareHand), new SkillBlock(), new SkillParry(), new SkillBodySlam(), new SkillStomp(), new SkillBoltLightning(), new SkillKickBasic());
		ai = new AISimple(this);
		addElements(new Element("Metal", 1));
	}

}
