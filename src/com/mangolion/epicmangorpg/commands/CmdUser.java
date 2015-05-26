package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;

public class CmdUser extends CommandCombo {

	public CmdUser(LinkedList<CommandHandler> previous) {
		super(previous);
		setEnabled(false);
	}
	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> result = new LinkedList<Command>();
		for (Character character: Game.getInstance().charsAllies){
			result.add(new Command(character.name, Command.COMBOBOX){
				@Override
				public CommandHandler getNextCommand() {
					return new CmdUse(previous);
				}
			});
		}
		return result;
	}

}
