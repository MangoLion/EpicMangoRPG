package com.mangolion.epicmangorpg.characters;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.mangolion.epicmangorpg.commands.CmdUser;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.ItemPortalStoneSaved;
import com.mangolion.epicmangorpg.items.ItemStack;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBarrelRoll;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillCharge;
import com.mangolion.epicmangorpg.skills.SkillFireCoating;
import com.mangolion.epicmangorpg.skills.SkillJumpBack;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillUseItem;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.skills.Skills;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Armor;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.CopperDagger;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.WandPine;
import com.mangolion.epicmangorpg.weapons.Weapon;

public class CharacterPlayer extends Character {
	public static Character instance;
	static Weapon weapon = new LongSword();
	public CharacterPlayer(String name) {
		super(name, "", 60, 60, 80, 40, 10, 60, 20, 10,0, 0,new Barehands(),
				new SkillBlock(), new SkillKickBasic(), new SkillBarrelRoll(),  new SkillSlashBasic(),
				new SkillParry(), new SkillStab(), new SkillWait(), new SkillFireCoating(), new SkillSideStep(), new SkillRespite(),
				new SkillCharge(), new SkillJumpBack(), new SkillUseItem()/*, new SkillStab(), new SkillSummonGolem(), new SkillFocus(), new SkillSlashSpin(),
				new SkillRoar(), new SkillStormFire(), new SkillBoltThorn(), new SkillBoltWind()*/);
		isPlayer = true;
		isAllied = true;
		instance = this;
		learnRate = 0.5f;
		inventory.addItem(Items.portalStone, 1);

		//cpBase = -200;
		//addElements(new Element(Elements.Fire, 1));
	}
	
	public void init(){
		//for (Skill skill:Skills.all)
		//	addSkills(skill);
		changeCrystal(60);
		inventory.addItem(Items.potionSmall, 5);
		inventory.addItem(Items.arrow, 100);
		inventory.addItem(Items.bullet, 100);
		inventory.addItem(Items.crystalEarth, 50);
		inventory.addItem(Items.crystalFire, 50);
		inventory.addItem(Items.crystalWater, 50);
		inventory.addItem(Items.crystalWind, 50);
		inventory.addItem(Items.grenadeAmmo, 50);
		inventory.addItem(weapon);
		inventory.addItem(Items.portalStone, 2);
		//inventory.addItem(new CopperDagger());
		//inventory.addItem(new WandPine());
		/*for (ItemCustom item: Items.customs)
			if (!(item instanceof ItemPortalStoneSaved))
				inventory.addItem(item);*/
	}

	
	@Override
	public void addSkills(Skill... skills) {
		super.addSkills(skills);
		FrameGame frame = FrameGame.instance;
		if (frame != null && Game.getInstance() != null)
			frame.setCommand(new CmdUser(null));
	}

	


	

}
