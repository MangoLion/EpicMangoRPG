package com.mangolion.epicmangorpg.grave;

import com.mangolion.epicmangorpg.ais.AIFlight;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.daybreak.SkillDisintegrationBeam;
import com.mangolion.epicmangorpg.daybreak.SkillEtherSeeker;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBoltFire;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillBoltLightning;
import com.mangolion.epicmangorpg.skills.SkillBoltRock;
import com.mangolion.epicmangorpg.skills.SkillBoltThorn;
import com.mangolion.epicmangorpg.skills.SkillBoltWater;
import com.mangolion.epicmangorpg.skills.SkillBoltWind;
import com.mangolion.epicmangorpg.skills.SkillFlight;
import com.mangolion.epicmangorpg.skills.SkillJumpBack;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillMeditate;
import com.mangolion.epicmangorpg.skills.SkillMillionSlash;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillSideJump;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashSpin;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillStormFire;
import com.mangolion.epicmangorpg.skills.SkillStormIce;
import com.mangolion.epicmangorpg.skills.SkillStormSand;
import com.mangolion.epicmangorpg.skills.SkillStormWind;
import com.mangolion.epicmangorpg.statuses.BuffAirborne;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class Lute extends Character{

	public Lute() {
		super("Lute", "", 200, 300, 150, 80, 40, 200, 90, 30, 10, 5, new SwordWand(),
				new SkillStab(), new SkillSlashBasic(), new SkillParry(), 
				new SkillBlock(), new SkillSideStep(), new SkillSideJump(), new SkillJumpBack(), new SkillKickBasic(), new SkillRespite(), new SkillMeditate(),
				new SkillBoltFire(), new SkillBoltIce(), new SkillBoltLightning(), new SkillBoltRock(), new SkillBoltThorn(), new SkillBoltWater(), new SkillBoltWind(),
				new SkillStormFire(), new SkillStormIce(), new SkillStormSand(), new SkillStormWind(), new SkillEtherSeeker(), new SkillDisintegrationBeam(),
				new SkillFlight());
		pronoun = "she";
		pronoun2 = "her";
		pronoun3 = "her";
		pronoun4 = "her";
		applyBuff(new BuffAirborne(10));
		addBarrier(new Barrier(this, "Physical Armor", 80, 10, 10, 5, 100, 1, Barrier.MELEE, new Element("Light", 1)));
		addBarrier(new Barrier(this, "Magic Armor", 80, 10, 10, 5, 100, 1, Barrier.MAGIC, new Element("Light", 1)));
		addBarrier(new Barrier(this, "Reinforced Armor", 50, 10, 10, 5, 100, 1, Barrier.ALL, new Element("Light", 1)));
		
		ai = new AIFlight(this);
	}
	public static class SwordWand extends Weapon{

		public SwordWand() {
			super("Holy Sword Wand", 10, 10, 1, 1, 1,  Weapons.Sword, Weapons.Wand, Weapons.Bladed);
			setElements(new Element("Light", 1));
			price = 15;
			magicDamage =20;
			baseMagicDmgMod = 1.1f;
		}

	}
}

