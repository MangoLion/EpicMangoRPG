package com.mangolion.epicmangorpg.game;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.Themes;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.ItemStack;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.Skills;
import com.mangolion.epicmangorpg.steps.Step;

public class WiniWriter {
	public static void saveProfile(Profile profile){
		File file = new File( FrameGame.profileFile.getAbsolutePath() + "\\\\" + profile.getName());
		if (!file.exists())
			file.mkdirs();
		File game = new File(file.getAbsolutePath() + "\\\\" + "game.txt" );
		
		try {
			if (!game.exists())
				game.createNewFile();
			else {
				game.delete();
				game.createNewFile();
			}
			
			Wini winiGame = new Wini(game);
			winiGame.put("game", "current floor", profile.currentFloor);
			winiGame.put("game", "last floor", profile.lastFloor);
			winiGame.put("game", "next floor", profile.nextFloor);
			winiGame.put("game", "floor percent", profile.floorPercent);
			winiGame.put("game", "theme", profile.theme);
			winiGame.store();
		
		File playerFile = new File(file.getAbsolutePath() + "\\\\" + "player.txt" );

			if (!playerFile.exists())
				playerFile.createNewFile();
			else {
				playerFile.delete();
				playerFile.createNewFile();
			}
			
			CharacterPlayer player = profile.player;
			Wini wini = new Wini(playerFile);
			wini.put("general info", "Name", player.name);
			wini.put("general info", "Gender", player.gender);
			wini.put("general info", "hp", player.maxHP);
			wini.put("general info", "mp", player.maxMP);
			wini.put("general info", "sp", player.maxSP);
			wini.put("general info", "bal", player.maxBal);
			wini.put("general info", "str", player.str);
			wini.put("general info", "dex", player.dex);
			wini.put("general info", "int", player.inte);
			wini.put("general info", "agi", player.agi);
			wini.put("general info", "def", player.def);
			wini.put("general info", "prot", player.prot);
			
			String str = "";
			for (ItemStack stack : player.inventory.itemStacks)
				str += stack.item.name + "|" + stack.stack + "|";
			wini.put("Inventory", "Consumables", str);
			
			str = "";
			for (ItemCustom item: player.inventory.itemCustoms){
				str += item.name + "|";
				wini.put(item.name, "durability", item.durability);
				wini.put(item.name, "isEquipted", player.isEquipted(item));
			}
			wini.put("Inventory", "ItemCustoms", str);
			
			
			String skills = "";
			for (Skill skill: player.skills)
				skills += skill.name + "|";
			wini.put("general info", "skills", skills);
			
			for (Skill skill: player.skills){
				wini.put(skill.name, "prof", skill.prof);
				for (Step step: skill.steps)
					wini.put(skill.name,"Step "+ skill.steps.indexOf(step), step.prof);
				wini.store();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Profile readProfile(String name){
		Profile profile = null;
		File file = new File( FrameGame.profileFile.getAbsolutePath() + "\\\\" + name);
		if (!file.exists())
			return null;
		
		File game = new File(file.getAbsolutePath() + "\\\\" + "game.txt" );
		try {
			if (!game.exists())
				game.createNewFile();
			
			Wini winiGame = new Wini(game);
			 int currentFloor =winiGame.get("game", "current floor", Integer.class);
			float floorPercent =	winiGame.get("game", "floor percent", Float.class);
			boolean nextFloor = 		winiGame.get("game", "next floor", Boolean.class)
			, lastFloor = winiGame.get("game", "last floor", Boolean.class);
			String theme = winiGame.get("game", "theme");
			
			
			
			File playerFile = new File(file.getAbsolutePath() + "\\\\" + "player.txt" );

			if (!playerFile.exists())
				playerFile.createNewFile();
			
			CharacterPlayer player =  new CharacterPlayer(name);
			player.skills.clear();
			
			Wini wini = new Wini(playerFile);
			player.gender = wini.get("general info", "Gender");
			player.maxHP = wini.get("general info", "hp",Float.class);
			player.maxMP =wini.get("general info", "mp", Float.class);
			player.maxSP = wini.get("general info", "sp", Float.class);
			player.maxBal = wini.get("general info", "bal", Float.class);
			player.str = wini.get("general info", "str", Float.class);
			player.dex = wini.get("general info", "dex", Float.class);
			player.inte = wini.get("general info", "int",Float.class);
			player.agi = wini.get("general info", "agi", Float.class);
			player.def = wini.get("general info", "def",Float.class);
			player.prot = wini.get("general info", "prot", Float.class);
			
			String items = wini.get("Inventory", "Consumables");
			
			boolean getStack = false;
			ItemStack stack = null;
			
			for (String str: items.split("\\|")){
				if (!getStack){
					Item item = Items.getItem(str);
					if (item != null)
						stack = new ItemStack(player.inventory, item, 0);
					getStack = true;
							
				}else {
					stack.stack = Integer.valueOf(str);
					getStack = false;
					player.inventory.itemStacks.add(stack);
				}
			}
			
			String skills = wini.get("general info", "skills");
			for (String str: skills.split("\\|")){
				Skill skill = Skills.getSkill(str);
				if (skill == null)
					continue;
				System.out.println(skill.name);
				skill.prof = wini.get(skill.name, "prof", Float.class);
				for (int i = 0; i < skill.steps.size(); i ++){
					Step step = skill.steps.get(i);
					step.prof = wini.get(skill.name, "Step " + i, Float.class );
				}
				player.addSkills(skill);
			}
			
			items = wini.get("Inventory", "ItemCustoms");
			for (String str: items.split("\\|")){
				ItemCustom item = Items.getItemCustom(str);
				if (item != null)
					item = Utility.getInstance(item.getClass());
				else
					continue;
			//	System.out.println(player.name + " "+ item.name);
				float dur = wini.get(item.name, "durability", Float.class);
				item.durability = dur;
				player.inventory.addItem(item);
				if (wini.get(item.name, "isEquipted", Boolean.class)){
					player.equip(item);
				}
			}

				
				profile = new Profile(player, currentFloor, floorPercent, nextFloor, lastFloor, theme);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profile;
	}
	
	public static void deleteProfile(String name){
		File file = new File( FrameGame.profileFile.getAbsolutePath() + "\\\\" + name);
		File game = new File(file.getAbsolutePath() + "\\\\" + "game.txt" );
		File playerFile = new File(file.getAbsolutePath() + "\\\\" + "player.txt" );
		game.delete();
		playerFile.delete();
		file.delete();
		

	}
}
