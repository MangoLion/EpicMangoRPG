package com.mangolion.epicmangorpg.characters;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBarrelRoll;
import com.mangolion.epicmangorpg.skills.SkillBasicHeal;
import com.mangolion.epicmangorpg.skills.SkillBasicKick;
import com.mangolion.epicmangorpg.skills.SkillBasicSlash;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillIceBolt;
import com.mangolion.epicmangorpg.skills.SkillShootArrow;
import com.mangolion.epicmangorpg.skills.SkillMillionSlash;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.weapons.WeaponCopperDagger;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;

public class CharacterPlayer extends Character {
	public static CharacterPlayer instance;

	public CharacterPlayer(String name) {
		super(name, "", 60, 40, 60, 40, 10, 60, 20, 10, 0, 0,
				new WeaponLongSword(), new SkillBasicSwordCombo(),
				new SkillBlock(), new SkillBasicKick(), new SkillBarrelRoll(),
				new SkillParry(), new SkillStab(), new SkillBasicSlash(), new SkillWait(), new SkillSidestepSlash());
		isPlayer = true;
		isAllied = true;
		instance = this;
		inventory.addItem(Items.potionSmall, 5);
		learnRate = 0.5f;
	}

	@Override
	public void nextAction() {
		/*
		 * if (target == null) target = Game.getInstance().findEnemy(this);
		 * skills.getLast().execute();
		 */
		if (!isStunned())
			Game.getInstance().timer.stop();
		else
			System.out.println("Stunned");
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
		System.out.println("reseted");
	}
}
