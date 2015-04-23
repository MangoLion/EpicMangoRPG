package com.mangolion.epicmangorpg.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.frames.FrameGameInfo;
import com.mangolion.epicmangorpg.game.Game;

public class MenuHelp extends JMenu {
	public MenuHelp() {
		super("Help");
		
		JMenuItem mInfo = new JMenuItem("Game Info");
		mInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameGameInfo info = new FrameGameInfo();
			}
		});
		
		add(mInfo);
	}
}
