package com.mangolion.epicmangorpg.game;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

import com.mangolion.epicmangorpg.characters.Character;
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
			
			Character player = profile.player;
			Wini wini = player.toWini(playerFile);
			wini.store();
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
			player.loadWini(wini);


				
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
