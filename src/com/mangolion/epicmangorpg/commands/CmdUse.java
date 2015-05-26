package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;

public class CmdUse extends CommandCombo {

	public CmdUse(LinkedList<CommandHandler> previous) {
		super(previous);

	}
	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> result = new LinkedList<Command>();
		if (Game.getInstance().currentFloor != 0 || Game.getInstance().timeLimit <= 15)
		result.add(new Command("Use", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdSkillItem(previous);
			}
		});
		result.add(new Command("Enter", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdEnter(previous);
			}
		});
		return result;
	}

}
