package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillBoltRock;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillStomp;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class GolemIce extends Character{

	public GolemIce() {
		super("Ice Golem", "",  80, 60, 120, 50, 5, 100, 20, 15, 5, 2, new Weapon("Ice", 5, 0, 1.2f, 1, 1.2f, Weapons.BareHand), new SkillBlock(), new SkillParry(), new SkillBodySlam(), new SkillStomp(), new SkillBoltIce(), new SkillKickBasic());
		ai = new AISimple(this);
		addElements(new Element("Ice", 1));
	}

}
