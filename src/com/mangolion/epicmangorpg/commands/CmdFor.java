package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdFor extends CommandInput{

	public CmdFor(LinkedList<CommandHandler> previous) {
		super(previous);
		System.out.println("created");
	}
	
	@Override
	public Command getSelectedCommand() {
		final Character character = CharacterPlayer.instance;//Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
		final Skill skill = character.getSkill(previous.get(3).getSelectedCommand().text);
		return new Command("Input", Command.INPUT) {
			@Override
			public boolean execute() {
				return skill.execute(null, Float.valueOf(getText()));
			}
		};
	}
	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> results = new LinkedList<Command>();
		results.add(getSelectedCommand());
		return results;
	}
	
}
