package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AIFlight;
import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillFlight;
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
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillSting;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class BeeBumble extends Character{

	public BeeBumble() {
		super("Bumble Bee", "Fast and deadly",80, 20, 100, 100, 20, 30, 30, 10, 0, 0,new Weapon("Stinger", 0, 100, Weapons.Fang,0.7f, 1, 0.8f),  new SkillFlight(), new SkillBodySlam(), new SkillParry(), new SkillSting(), new SkillDodge());
		addElements(new Element("Furry", 1));
		ai = new AIFlight(this);
		setAirborne(10);
		
		addDrop(Items.feather, 0.7f, 1);
		addDrop(Items.mstone, 0.6f, 1);
	}
}
