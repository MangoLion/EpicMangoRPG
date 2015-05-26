package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.frames.FrameSkills;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdSkill extends CommandCombo{

	String skill = "";
	public CmdSkill(final LinkedList<CommandHandler> previous) {
		super(previous);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Character character = CharacterPlayer.instance;//Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
				skill = new FrameSkills(character).showDialog();
				refresh();
				CommandHandler next = getSelectedCommand().getNextCommand();
				if (next != null)
					FrameGame.getInstance().setCommand(next);
				super.mousePressed(e);
			}
	
		});
	}
	
	@Override
	public Command getSelectedCommand() {
		return (Command) getSubCommands().getFirst();
	}

	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> results = new LinkedList<Command>();
		final Character character = CharacterPlayer.instance;//Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
		if (skill == null || (skill != null && skill.equals("")))
			results.add(new Command(character.skills.getFirst().name, Command.COMBOBOX) {
				
				@Override
				public boolean execute() {
					// TODO Auto-generated method stub
					return character.skills.getFirst().execute();
				}
				
				@Override
				public CommandHandler getNextCommand() {
					// TODO Auto-generated method stub
					return new CmdOnWithFor(previous);
				}
			});
		else
		for (final Skill skill: character.skills){
			if (this.skill.equals(skill.name))
			results.add(new Command(skill.name, Command.COMBOBOX) {
				
				@Override
				public boolean execute() {
					// TODO Auto-generated method stub
					return skill.execute();
				}
				
				@Override
				public CommandHandler getNextCommand() {
					// TODO Auto-generated method stub
					return new CmdOnWithFor(previous);
				}
			});
		}
		return results;
	}
}
