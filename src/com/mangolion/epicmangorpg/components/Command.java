package com.mangolion.epicmangorpg.components;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class Command {
	public static void execute(String cmd) {
		LinkedList<String> words = new LinkedList<String>(Arrays.asList(cmd.toLowerCase()
				.split(" ")));
		Iterator<String> i = words.iterator();
		
		Character control;
		while (i.hasNext()) {
			if (i.next().equals("use")) {
				control = CharacterPlayer.instance;
				String next = i.next();
				if (next.equals("item")){
					next = "";
					String itemName = "", target;
					while (!next.equals("on")){
						itemName += next;
						if (i.hasNext())
							next = i.next();
						else 
							break;
					}
					if (i.hasNext())
						target = i.next();
					else
						target = CharacterPlayer.instance.name;
					CharacterPlayer.instance.setTarget(Game.getInstance().getCharacter(target));
					CharacterPlayer.instance.inventory.getItem(itemName.replace(" ", "")).use(control, Game.getInstance().getCharacter(target));
					return;					
				}
				if (next.equals("skill")){
					next = "";
					String skillName = "", target = null;
					while (!next.equals("on")){
						skillName += next;
						if ( i.hasNext())
							next = i.next();
						else
							break;
					}
					if (i.hasNext())
						target = i.next();
					if (Game.getInstance().getCharacter(target) == null){
						System.out.println(target);
						return;
					}
					
					CharacterPlayer.instance.setTarget(Game.getInstance().getCharacter(target));
					if (!i.hasNext()){
					for (Skill skill: control.skills)
						if (skill.name.replace(" ", "").toLowerCase().equals(skillName)){
							if (skill.execute(Game.getInstance().getCharacter(target)))
							Game.getInstance().timer.start();
						}
					}
						else{
							cmd = i.next();
							if (cmd.equals("for")){
								float time = Float.parseFloat(i.next());
								for (Skill skill: control.skills)
									if (skill.name.replace(" ", "").toLowerCase().equals(skillName)){
										if (skill.execute(Game.getInstance().getCharacter(target), time))
										Game.getInstance().timer.start();
									}
							}else if(cmd.equals("with")){
								String aug = i.next();
								for (Skill skill: control.skills)
									if (skill.name.replace(" ", "").toLowerCase().equals(skillName)){
										if (skill.execute(Game.getInstance().getCharacter(target), aug))
										Game.getInstance().timer.start();
									}
							}
						}
				}
			}
		}
	}
}
