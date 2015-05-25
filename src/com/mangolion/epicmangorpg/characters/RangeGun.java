package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillBulletSpray;
import com.mangolion.epicmangorpg.skills.SkillHardAim;
import com.mangolion.epicmangorpg.skills.SkillSidestepShoot;
import com.mangolion.epicmangorpg.skills.SkillSniperShot;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.RifleBasic;
import com.mangolion.epicmangorpg.weapons.BowShort;

public class RangeGun extends Character{

	public RangeGun() {
		super("Shooting Range", "A place for newbie gunners", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, new Barehands(), new SkillHardAim(), new SkillBulletSpray(),  new SkillSniperShot(), new SkillSidestepShoot());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		// TODO Auto-generated method stub
		new FrameShopSkill("Shooting Range",new RifleBasic(), skills, Items.bullet);
	}

}
