package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillRoar;
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
import com.mangolion.epicmangorpg.skills.SkillStomp;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class TurtleRock extends Character{

	public TurtleRock() {
		super("Rock Turtle", "A giant turtle that has a shell made out of rocks. Slow but powerful", 250, 100,350, 70,10, 300, 40, 40, 10, 30,new Weapon("Hard Shell", 15, 100, Weapons.BareHand, 1.3f, 1,1f),  new SkillBite(), new SkillBodySlam(),new SkillStomp(), new SkillRoar());
		ai = new AISimple(this);
		addElements(new Element("Tough Hide", 1));
		addBarrier(new Barrier(this, "Hard Shell", 100, 10,5, 5, 100, 0.6f, new Element("Tough Hide", 1)));
		cpBase = 200;
	}
}
