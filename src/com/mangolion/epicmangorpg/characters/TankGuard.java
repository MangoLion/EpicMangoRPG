package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillBoltLightning;
import com.mangolion.epicmangorpg.skills.SkillBoltRock;
import com.mangolion.epicmangorpg.skills.SkillGunShoot;
import com.mangolion.epicmangorpg.skills.SkillJamFix;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillLaunchGrenade;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillReload;
import com.mangolion.epicmangorpg.skills.SkillShotQuick;
import com.mangolion.epicmangorpg.skills.SkillSidestepShoot;
import com.mangolion.epicmangorpg.skills.SkillSniperShot;
import com.mangolion.epicmangorpg.skills.SkillStomp;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class TankGuard extends Character{
	public TankGuard() {
		super("Guard Tank", "A tough, sturdy tank designed to guard the sacred floors of the dungeon. It's body slam skill has much faster cooldown time.",  200, 150, 350, 120, 15, 300, 20, 40, 25, 15, new Turret(), 
				 new SkillLaunchGrenade(), new SkillShotQuick(), new SkillReload(), new SkillJamFix(), new SkillParry(), new SkillSidestepShoot());
		SkillBodySlam bodySlam = new SkillBodySlam();
		bodySlam.steps.getFirst().timeCooldown = 0.3f;
		addSkills(bodySlam);
		ai = new AISimple(this);
		addElements(new Element("Metal", 1));
		addBarrier(new Barrier(this, "Metal Armor", 100, 10, 5, 5, 100, 0.7f, new Element("Metal", 1)));
		inventory.addItem(Items.bullet, 300);
		inventory.addItem(Items.grenadeAmmo, 300);
	}

	public static class Turret extends Weapon{

		public Turret() {
			super("Tank Turret", 10, 100, 1.2f, 2,
					1, Weapons.LauncherGrenade, Weapons.Reloadable, Weapons.Gun);
			gunDamage = 15;
			launcherDamage = 45;
			canToggleAutomatic = false;
			setReload(12, 0.7f, 3, 0.05f);
			isAutomatic = true;
		}
		
		@Override
		public float getAccuracyBuff() {
			// TODO Auto-generated method stub
			return -0.1f;
		}
		
	}
}
