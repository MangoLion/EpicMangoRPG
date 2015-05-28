package com.mangolion.epicmangorpg.components;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;

public enum Style {
	dmg, time, cover, crit, miss, parry, interrupt;
	
	static String msgs[] = {"Q.Q", "Awful", "Flat Out Boring", "You can do better", "Try Harder", "Okay", "Blast", "Alright", "Cool", "Nice", "Sweet", "Stylish", "Impressive", "Beautiful",  "Stunning", "Amazement", "Awesome!", "Magnificent", "Majestic!", "SSSSmoking Hot Style!"};
	
	static javax.swing.text.Style getStyle(float style){
		if (style < 7)
			return StylePainter.NORMAL;
		if (style < 14)
			return StylePainter.STATUS;
		return StylePainter.NAME;
	}
	
	public static String getMsg(float style){
		int s = Math.round(style);
		if (s >= 20)
			s = 19;
		if (s < 0)
			s = 0;
		return msgs[s];
	}
	
	/*public static LinkedList<StyleSegment> getSegments(float style){
		LinkedList<StyleSegment> result = new LinkedList<StyleSegment>();
		result.add(new StyleSegment(StylePainter.NORMAL, "["));
		result.add(new StyleSegment(getStyle(style), getMsg(style)));
		result.add(new StyleSegment(StylePainter.NORMAL, "]", true));
		return result;
	}*/
	
	public static LinkedList<StyleSegment> getSegments(float style, Character character){
		LinkedList<StyleSegment> result = new LinkedList<StyleSegment>();
		result.add(new StyleSegment(StylePainter.NORMAL, "["));
		float add = 6;//(0>style)?-2:2;
		result.add(new StyleSegment(getStyle((add + style*3)), getMsg((add + style*3))));
		result.add(new StyleSegment(StylePainter.NORMAL, "("));
		result.add(new StyleSegment(getStyle(character.style), getMsg(character.style)));
		result.add(new StyleSegment(StylePainter.NORMAL, ")", false));
		result.add(new StyleSegment(StylePainter.NORMAL, "]", true));
		return result;
	}

	public static float positive(Character source, Character target, Style type, float ... value) {
		float result = 0;
		Proficiency prof = new Proficiency(source, target);
		switch (type) {
		case dmg:
			if (value[0] < 0)
				value[0] = 1;
			result =2* value[0]/source.getMaxHP();
			result *= 1 + value[1];
			System.out.println("dmg extra " + (1 + value[1]));
			break;
		case time:
			//args: delta time, total time
			result = value[0] + value[0]*(1 - value[1]/value[0]);
			break;
		case cover:
			result = 5*value[0]/source.getMaxHP();
			break;
		case crit:
			result = 5*(1 - source.getCritical(target));
			break;
		case miss:
			result -= value[0];
			result *= 1 + value[1]/target.getMaxHP();
			break;
		case parry:
			result = (source.getStr()/target.getStr());
			break;
		case interrupt:
			result = 5*value[0];
			break;
		default:
			break;
		}
		 result *=prof.type/2f;
		 result *= 1 + source.getHp()/source.getMaxHP();
		 source.changeStyle(result);
		negative(target, source, type, value);
		return result;
	}

	public static void negative(Character source, Character target, Style type, float ... value) {
		float result = 0;
		Proficiency prof = new Proficiency(source, target);
		switch (type) {
		case dmg:
			if (value[0] < 0)
				value[0] = 1;
			result = -2* value[0]/target.getMaxHP();
			result *= 1 + value[1];
			break;
		case time:
			//args: delta time, total time
			result = -value[0] + value[0]*(1 - value[1]/value[0]);
			break;
		case cover:
			result =- 5*value[0]/source.getMaxHP();
			break;
		case crit:
			result = -5*(1 - target.getCritical(source));
			break;
		case miss:
			result += value[0];
			result *= 1 + value[1]/target.getMaxHP();
			break;
		case parry:
			result = -(target.getStr()/source.getStr());
			break;
		case interrupt:
			result = -5*value[0];
			break;
		default:
			break;
		}
		prof.invert();
		 result *=prof.type/2f;
		source.changeStyle(result);
		
	}
}
