package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;

public class CmdSkillItem extends CommandCombo {

	public CmdSkillItem(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> result = new LinkedList<Command>();
		result.add(new Command("Skill", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdSkill(previous);
			}
		});
		
		result.add(new Command("Item", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdItem(previous);
			}
		});
		return result;
	}

}
