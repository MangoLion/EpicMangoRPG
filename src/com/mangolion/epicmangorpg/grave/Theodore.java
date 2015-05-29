package com.mangolion.epicmangorpg.grave;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.daybreak.SkillIgnitionDispel;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBoltFire;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillBoltWind;
import com.mangolion.epicmangorpg.skills.SkillCharge;
import com.mangolion.epicmangorpg.skills.SkillHardAim;
import com.mangolion.epicmangorpg.skills.SkillJamFix;
import com.mangolion.epicmangorpg.skills.SkillJumpBack;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillLaunchGrenade;
import com.mangolion.epicmangorpg.skills.SkillMeditate;
import com.mangolion.epicmangorpg.skills.SkillMillionSlash;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillReload;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillShotQuick;
import com.mangolion.epicmangorpg.skills.SkillSideJump;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSidestepShoot;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashSpin;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.GunBlade;
import com.mangolion.epicmangorpg.weapons.Weapon;

public class Theodore extends Character{

	public Theodore() {
		super("Theodore", "", 200, 300, 200, 100, 30, 200, 100, 30, 20, 10, new GunBlade(),
				new SkillReload(), new SkillJamFix(), new SkillShotQuick(), new SkillLaunchGrenade(), new SkillSidestepShoot(), new SkillCharge(),
				new SkillMillionSlash(), new SkillStab(), new SkillSlashBasic(), new SkillParry(), 
				new SkillBlock(), new SkillSideStep(), new SkillSideJump(), new SkillJumpBack(), new SkillKickBasic(), new SkillRespite(), new SkillMeditate());
		addBarrier(new Barrier(this, "Reinforced Armor", 100, 10, 10, 5, 100, 1, Barrier.ALL));
		inventory.addItem(Items.bullet, 300);
		inventory.addItem(Items.grenadeAmmo, 30);
		ai = new AISimple(this);
	}

}
