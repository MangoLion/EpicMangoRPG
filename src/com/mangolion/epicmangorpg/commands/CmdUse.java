package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;

public class CmdUse extends CommandCombo {

	public CmdUse(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> result = new LinkedList<Command>();
		result.add(new Command("Use", Command.COMBOBOX, null){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdSkillItem(previous);
			}
		});
		result.add(new Command("Enter", Command.COMBOBOX, null){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdEnter(previous);
			}
		});
		return result;
	}

}
