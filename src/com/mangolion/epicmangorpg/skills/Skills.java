package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.game.Utility;

public class Skills {
	public static Skill masteries[] = {new SkillMaterySword(), new SkillCombatMastery()},
			all[] =  {new SkillAvalancheSand(), new SkillBarrelRoll(), new SkillBasicSwordCombo(), new SkillBite(), new SkillBlock(), new SkillBodySlam(), new SkillBoltFire(), new SkillBoltIce(), new SkillBoltLightning(),  new SkillBoltWater(),
		new SkillClawSnap(), new SkillCombatMastery(), new SkillDodge(), new SkillFireCoating(), new SkillHealBasic(), new SkillJumpAtk(), new SkillKickBasic(), new SkillMaterySword(), new SkillMillionSlash(), new SkillParry(), new SkillBoltRock(),
		new SkillShootArrow(), new SkillSideJump(), new SkillSideStep(), new SkillSidestepSlash(), new SkillSlashBasic(), new SkillSlashHeavy(), new SkillSpitPoison(), new SkillStab(), new SkillSting(), new SkillStomp(), new SkillTailSwing(), new SkillWait(), 
		new SkillWebFiring(), new SkillRespite(), new SkillMeditate()}; 
	
	public static Skill getSkill(String name){
		for (Skill skill: all)
			if (skill.name.equals(name))
				return Utility.getInstance(skill.getClass());
		return null;
	}
}
