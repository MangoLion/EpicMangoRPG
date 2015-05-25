package com.mangolion.epicmangorpg.characters;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.mangolion.epicmangorpg.commands.CmdUser;
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
	public static CharacterPlayer instance;
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
		for (Skill skill:Skills.all)
			addSkills(skill);
		changeCrystal(50);
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
		for (ItemCustom item: Items.customs)
			if (!(item instanceof ItemPortalStoneSaved))
				inventory.addItem(item);
	}

	@Override
	public void nextAction() {
		/*
		 * if (target == null) target = Game.getInstance().findEnemy(this);
		 * skills.getLast().execute();
		 */
		if (!isStunned())
			Game.getInstance().timer.stop();
	}
	
	@Override
	public void addSkills(Skill... skills) {
		FrameGame frame = FrameGame.instance;
		if (frame != null)
			frame.setCommand(new CmdUser(null));
		super.addSkills(skills);
	}

	public void reset() {
		this.hp = getMaxHP();
		this.mp = getMaxMP();
		this.sp  = getMaxSP();
		this.bal = getMaxBal();
		statuses = new LinkedList<Status>();
		style = 10;
		summon = null;
		buffs.clear();
		skillCharged = null;
		buffs.clear();
		for (Skill skill :skills)
			skill.reset();
	}

	public boolean isEquipted(ItemCustom item) {
		if (item instanceof Weapon)
			return isEquipted((Weapon) item);
		if (item instanceof Armor)
			return isEquipted((Armor) item);
		return false;
	}
	
	public Wini toWini(File file) throws InvalidFileFormatException, IOException{
		Wini wini = new Wini(file);
		wini.put("general info", "Name", name);
		wini.put("general info", "Gender", gender);
		wini.put("general info", "hp", maxHP);
		wini.put("general info", "mp", maxMP);
		wini.put("general info", "sp", maxSP);
		wini.put("general info", "bal", maxBal);
		wini.put("general info", "str", str);
		wini.put("general info", "dex", dex);
		wini.put("general info", "int", inte);
		wini.put("general info", "agi", agi);
		wini.put("general info", "def", def);
		wini.put("general info", "prot", prot);
		wini.put("general info", "crystal", crystals);
		
		String str = "";
		for (ItemStack stack : inventory.itemStacks)
			str += stack.item.name + "|" + stack.stack + "|";
		wini.put("Inventory", "Consumables", str);
		
		str = "";
		for (ItemCustom item: inventory.itemCustoms){
			str += item.name + "|";
			wini.put(item.name, "durability", item.durability);
			wini.put(item.name, "isEquipted", isEquipted(item));
			for (int i = 0; i < item.valueNum; i ++)
				wini.put(item.name, "value " + i, item.values.get(i));
		}
		wini.put("Inventory", "ItemCustoms", str);
		
		
		String skills = "";
		for (Skill skill: this.skills)
			skills += skill.name + "|";
		wini.put("general info", "skills", skills);
		
		for (Skill skill: this.skills){
			wini.put(skill.name, "prof", skill.prof);
			for (Step step: skill.steps)
				wini.put(skill.name,"Step "+ skill.steps.indexOf(step), step.prof);
		}
		return wini;
	}
	
	public void loadWini(Wini wini){
		gender = wini.get("general info", "Gender");
		maxHP = wini.get("general info", "hp",Float.class);
		maxMP =wini.get("general info", "mp", Float.class);
		maxSP = wini.get("general info", "sp", Float.class);
		maxBal = wini.get("general info", "bal", Float.class);
		str = wini.get("general info", "str", Float.class);
		dex = wini.get("general info", "dex", Float.class);
		inte = wini.get("general info", "int",Float.class);
		agi = wini.get("general info", "agi", Float.class);
		def = wini.get("general info", "def",Float.class);
		prot = wini.get("general info", "prot", Float.class);
		crystals = wini.get("general info", "crystal", Float.class);
		
		String items = wini.get("Inventory", "Consumables");
		
		boolean getStack = false;
		Item item = null;
		for (String str: items.split("\\|")){
			if (!getStack){
				item = Items.getItem(str);
				if (item != null)
					getStack = true;
						
			}else {
				int stack = Integer.valueOf(str);
				getStack = false;
				inventory.addItem(item,stack);
			}
		}
		
		String skills = wini.get("general info", "skills");
		for (String str: skills.split("\\|")){
			Skill skill = Skills.getSkill(str);
			if (skill == null)
				continue;
			skill.prof = wini.get(skill.name, "prof", Float.class);
			for (int i = 0; i < skill.steps.size(); i ++){
				Step step = skill.steps.get(i);
				step.prof = wini.get(skill.name, "Step " + i, Float.class );
			}
			addSkills(skill);
		}
		
		items = wini.get("Inventory", "ItemCustoms");
		for (String str: items.split("\\|")){
			ItemCustom itemC = Items.getItemCustom(str);
			if (itemC != null)
				itemC = Utility.getInstance(itemC.getClass());
			else
				continue;
		//	System.out.println(name + " "+ item.name);
			float dur = wini.get(itemC.name, "durability", Float.class);
			itemC.durability = dur;
			inventory.addItem(itemC);
			for (int i = 0; i < itemC.valueNum; i ++)
				itemC.values.add(wini.get(itemC.name, "value " + i));
			if (wini.get(itemC.name, "isEquipted", Boolean.class)){
				equip(itemC);
			}
		}
	}
}
