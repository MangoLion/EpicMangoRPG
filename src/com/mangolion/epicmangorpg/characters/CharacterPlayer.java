package com.mangolion.epicmangorpg.characters;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.armor.LeatherGloves;
import com.mangolion.epicmangorpg.armor.LeatherHat;
import com.mangolion.epicmangorpg.armor.LeatherJacket;
import com.mangolion.epicmangorpg.armor.LeatherPants;
import com.mangolion.epicmangorpg.armor.LeatherShoes;
import com.mangolion.epicmangorpg.armor.MetalBoots;
import com.mangolion.epicmangorpg.armor.MetalGuantlet;
import com.mangolion.epicmangorpg.armor.MetalHelm;
import com.mangolion.epicmangorpg.armor.PlateArmorLower;
import com.mangolion.epicmangorpg.armor.PlateArmorUpper;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBarrelRoll;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillFireCoating;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.weapons.Armor;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.WeaponCopperDagger;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;

public class CharacterPlayer extends Character {
	public static CharacterPlayer instance;
	static Weapon weapon = new WeaponLongSword();
	public CharacterPlayer(String name) {
		super(name, "", 60, 60, 80, 40, 10, 60, 20, 10,0, 0,weapon
				, new SkillBasicSwordCombo(),
				new SkillBlock(), new SkillKickBasic(), new SkillBarrelRoll(),
				new SkillParry(), new SkillStab(), new SkillSlashBasic(), new SkillWait(), new SkillSidestepSlash(), new SkillFireCoating(), new SkillSideStep(), new SkillRespite());
		isPlayer = true;
		isAllied = true;
		instance = this;

		learnRate = 0.5f;
		//addElements(new Element(Elements.Fire, 1));
	}
	
	public void init(){
		inventory.addItem(Items.potionSmall, 5);
		inventory.addItem(weapon);
		inventory.addItem(new WeaponCopperDagger());
		inventory.addItem(new LeatherGloves());
		inventory.addItem(new LeatherPants());
		inventory.addItem(new LeatherJacket());
		inventory.addItem(new LeatherHat());
		inventory.addItem(new LeatherShoes());
		inventory.addItem(new MetalBoots());
		inventory.addItem(new MetalGuantlet());
		inventory.addItem(new MetalHelm());
		inventory.addItem(new PlateArmorLower());
		inventory.addItem(new PlateArmorUpper());
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

	public void reset() {
		this.hp = getMaxHP();
		this.mp = getMaxMP();
		this.sp  = getMaxSP();
		this.bal = getMaxBal();
		statuses = new LinkedList<Status>();
		buffs.clear();
		for (Skill skill :skills)
			skill.reset();
	}

	public boolean isEquipted(ItemCustom item) {
		if (item instanceof Weapon)
			return isEquipted((Weapon) item);
		return isEquipted((Armor) item);
	}
}
