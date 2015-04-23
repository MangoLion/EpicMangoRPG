package com.mangolion.epicmangorpg.messages;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.text.Style;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;

public class Msg {
	public LinkedList<String> msgs = new LinkedList<String>();
	static Random rand = new Random();
	boolean nextline = true;
	
	public Msg(String... msgs) {
		if (msgs != null)
			this.msgs.addAll(Arrays.asList(msgs));
	}
	
	public Msg(boolean nextline,String... msgs) {
		this.nextline = nextline;
		if (msgs != null)
			this.msgs.addAll(Arrays.asList(msgs));
	}

	public LinkedList<StyleSegment>getMessage(Character character,
			Character target, float damage){
		LinkedList<StyleSegment> result = new LinkedList<StyleSegment>();
		String[] words;
		if (msgs.size() > 1)
			words = msgs.get(rand.nextInt(msgs.size() - 1)).split(" ");
		else 
			words = msgs.getFirst().split(" ");
		StyleSegment segment = new StyleSegment(StylePainter.NORMAL, "");
		for (String word: words){
			boolean foundToken = false;
			for (Token token: Token.tokens)
				if (word.replace("'s", "").replace(",", "").replace(".", "").equals(token.str)){
					result.add(segment);
					segment = new StyleSegment(token.type, "");
					String inStr = "";
					if (token == Token.name)
						inStr = character.name;
					if (token == Token.targetName)
						inStr = target.name;
					if (token == Token.skill)
						inStr = character.getCurrentStep().name;
					if (token == Token.targetSkill)
						inStr = target.getCurrentStep().name;
					if (token == Token.weapon)
						inStr = character.weapon.name;
					if (token == Token.targetWeapon)
						inStr = target.weapon.name;
					if (token == Token.p)
						inStr = character.pronoun;
					if (token == Token.p2)
						inStr = character.pronoun2;
					if (token == Token.p3)
						inStr = character.pronoun3;
					if (token == Token.tp)
						inStr =target.pronoun;
					if (token == Token.tp2)
						inStr = target.pronoun2;
					if (token == Token.tp3)
						inStr = target.pronoun3;
					if (token == Token.num)
						inStr = "" + damage;
					
					inStr += word.replace(token.str, "").trim();
					 segment.text =" "+ inStr;
					 result.add(segment);
					 segment = new StyleSegment(StylePainter.NORMAL, "");
					 foundToken = true;
					 break;
				}
			if (foundToken)
				continue;
			
			if (isNumeric(word.trim())){
				result.add(segment);
				segment = new StyleSegment(StylePainter.NUMBER, " " +  word);
				 result.add(segment);
				 segment = new StyleSegment(StylePainter.NORMAL, "");
				 continue;
			}
			
			segment.text += " " + word;
			if (word == words[words.length-1])
				result.add(segment);
		}
		if (nextline)
			result.getLast().text += "\n";
		return result;
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	static class Token {
		public static final Token name = new Token("$name", StylePainter.NAME),
				targetName = new Token("$targetname", StylePainter.NAME),
				weapon = new Token("$weapon", StylePainter.SKILL),
				targetWeapon = new Token("$targetweapon", StylePainter.SKILL),
				skill = new Token("$skill", StylePainter.SKILL),
				targetSkill = new Token("$targetskill", StylePainter.SKILL),
				p = new Token("$p", StylePainter.NORMAL),
				p2 = new Token("$p2", StylePainter.NORMAL),				
				p3 = new Token("$p3", StylePainter.NORMAL),
				tp = new Token("$tp", StylePainter.NORMAL),
				tp2 = new Token("$tp2", StylePainter.NORMAL),				
				tp3 = new Token("$tp3", StylePainter.NORMAL),
				num = new Token("$num", StylePainter.NAME),
				tokens[] = { name, targetName, weapon, targetWeapon, skill,
						targetSkill, p, p2, p3, tp, tp2, tp3, num};

		String str;
		Style type;

		public Token(String str, Style type) {
			this.str = str;
			this.type = type;
		}
	}
}
