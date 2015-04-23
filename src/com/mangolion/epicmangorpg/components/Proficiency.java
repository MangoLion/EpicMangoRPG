package com.mangolion.epicmangorpg.components;

import com.mangolion.epicmangorpg.characters.Character;

public class Proficiency {
	public static int VERYWEAK = 0,
			WEAK = 1,
			NORMAL = 2,
			STRONG = 3,
			AWFUL = 4,
			BOSS = 5;
	public int type = 0;
	public String str;
	public Character source, target;
	
	public Proficiency(Character source, Character target) {
		this.source = source;
		this.target = target;
		float cpS = source.getCP(),
				cpT = target.getCP();
		if (cpT < cpS*0.2){
			type = VERYWEAK;
			str = "Very Weak";
		}
		else if (cpT < cpS*0.5){
			type = WEAK;
			str = "Weak";
		}
		else if (cpT < cpS * 1.2){
			type = NORMAL;
			str = "Normal";
		}
		else if (cpT < cpS*1.6){
			type = STRONG;
			str = "Strong";
		}
		else{
			type = BOSS;
			str =" Boss";
		}
	}
	
}
