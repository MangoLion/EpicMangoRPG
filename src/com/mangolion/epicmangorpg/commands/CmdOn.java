package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdOn extends CommandCombo{

	public CmdOn(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> results = new LinkedList<Command>();
		for (Character character: Game.getInstance().charsEnemies){
			results.add(new Command(character.name, Command.COMBOBOX) {
				@Override
				public boolean execute() {
					Character character = Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text),
							target = Game.getInstance().getCharacter(previous.get(previous.size()-1).getSelectedCommand().text);
							character.setTarget(target);
					Skill skill = character.getSkill(previous.get(3).getSelectedCommand().text);
					return skill.execute(target);
				}
			});
		}
		
		return results;
	}
}
