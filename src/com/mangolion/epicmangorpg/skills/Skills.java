package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.game.Utility;

public class Skills {
	public static Skill masteries[] = {new SkillCombatMastery(), 
		//sword skills
		new SkillMasterySword(),/* new SkillStab(), new SkillSlashHeavy(), new SkillBasicSwordCombo(), new SkillMillionSlash(), new SkillSidestepSlash(),*/
		//bow skills
		new SkillMasteryBow(), new  SkillArrowShoot(),/*new SkillArrowPierce(), new SkillArrowRain(), new SkillArrowRapid(), new SkillArrowFlame(), new SkillArrowJumpBack(), new SkillBowSweep(), new SkillArrowSideStep(), new SkillArrowSwift(),*/
		//gun skills
		 new SkillMasteryGun(), new  SkillShotQuick(), new SkillReload(), new SkillToggleAutomatic(),new SkillJamFix(), /*new SkillHardAim(), new SkillBulletSpray(),  new SkillSniperShot(), new SkillSidestepShoot(),*/
		  new SkillLaunchGrenade(),
		 //CylinderSkills
		new SkillMasteryCylinder()/* new SkillWaterCannon(), new SkillBlastFlame(), new SkillSummonGolem(), new SkillBlastWind(), new SkillBlastSand()*/
		//magic
	},
		
		
			all[] =  {new SkillAvalancheSand(), new SkillBarrelRoll(), new SkillBasicSwordCombo(), new SkillBite(), new SkillBlock(), new SkillBodySlam(), new SkillBoltFire(), new SkillBoltIce(), new SkillBoltLightning(),  new SkillBoltWater(),
		new SkillClawSnap(), new SkillCombatMastery(), new SkillDodge(), new SkillFireCoating(), new SkillHealBasic(), new SkillJumpAtk(), new SkillKickBasic(), new SkillMasterySword(), new SkillJumpBack(), new SkillMillionSlash(), new SkillParry(), new SkillBoltRock(),
		new SkillArrowShoot(), new SkillSideJump(), new SkillSideStep(), new SkillSidestepSlash(), new SkillSlashBasic(), new SkillSlashHeavy(), new SkillSpitPoison(), new SkillStab(), new SkillSting(), new SkillStomp(), new SkillTailSwing(), new SkillWait(), 
		new SkillWebFiring(), new SkillRespite(), new SkillMeditate(), new SkillCharge(), new SkillArrowPierce(), new SkillArrowRain(), new SkillArrowRapid(), new SkillArrowShoot(),
		new SkillArrowFlame(), new SkillArrowJumpBack(), new SkillBowSweep(), new SkillArrowSideStep(), new SkillArrowSwift(), new SkillShotQuick() , new SkillReload(), new SkillJamFix() , new SkillHardAim(), new SkillBulletSpray()
		, new SkillToggleAutomatic(),  new SkillSniperShot(), new SkillSidestepShoot(), new SkillWaterCannon(), new SkillBlastFlame(), new SkillSummonGolem(), new SkillUseItem(), new SkillLaunchGrenade(), new SkillFocus(), new SkillSlashSpin(),
		new SkillRoar(),new SkillBoltWind(),new SkillBoltThorn(), new SkillStormFire(), new SkillStormIce(), new SkillStormWind(), new SkillStormSand(), new SkillSlashPrecise()}; 
	
	public static Skill getSkill(String name){
		for (Skill skill: all)
			if (skill.name.equals(name))
				return Utility.getInstance(skill.getClass());
		return null;
	}
}
