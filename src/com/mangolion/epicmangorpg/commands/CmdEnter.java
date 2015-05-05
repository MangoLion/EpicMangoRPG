package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdEnter extends CommandCombo{

	public CmdEnter(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	@Override
	public LinkedList<Command> getSubCommands() {
		System.out.println("" + selectedIndex);
		LinkedList<Command> results = new LinkedList<Command>();
		for (Character character: Game.getInstance().getAllChars()){
			if (character != CharacterPlayer.instance)
			results.add(new Command(character.name, Command.COMBOBOX) {
				@Override
				public boolean execute() {
					Character character = Game.getInstance().getCharacter(previous.get(2).getSelectedCommand().text);
					if (character.isShop)
						character.openShop();
					else
						Utility.narrate(character.name + " is not a shop!");
					return true;
				}
			});
		}
		
		return results;
	}
	
}
