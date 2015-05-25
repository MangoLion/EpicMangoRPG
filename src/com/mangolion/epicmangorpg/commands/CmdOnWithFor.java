package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdOnWithFor extends CommandCombo {

	public CmdOnWithFor(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> result = new LinkedList<Command>();
		Character character = CharacterPlayer.instance;//Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
		Skill skill = character.getSkill(previous.get(3).getSelectedCommand().text);
		if (skill.hasTarget)
		result.add(new Command("On", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdOn(previous);
			}
		});
		if (skill.hasArg)
		result.add(new Command("With", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdWith(previous);
			}
		});
		if (skill.customTime)
		result.add(new Command("For", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdFor(previous);
			}
		});
		return result;
	}

}
