package com.mangolion.epicmangorpg.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;

public class MenuSetting extends JMenu {
	public MenuSetting() {
		super("Setting");
		JMenu mTheme = new JMenu("Themes");
		JMenu mLight = new JMenu("Light");
		JMenu mDark = new JMenu("Dark");

		for (final String str : Themes.getLightThemeList()) {
			JMenuItem item = new JMenuItem(str);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Themes.setTheme(str);
				}
			});
			mLight.add(item);
		}

		for (final String str : Themes.getDarkThemeList()) {
			JMenuItem item = new JMenuItem(str);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Themes.setTheme(str);
				}
			});
			mDark.add(item);
		}

		mTheme.add(mLight);
		mTheme.add(mDark);
		add(mTheme);
		
		JMenuItem mMaxNumber = new JMenuItem("set max enemy numbers");
		mMaxNumber.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().maxEnemies =Integer.parseInt( JOptionPane.showInputDialog("Enter number: ", 0));
			}
		});
		
		add(mMaxNumber);
		
		JMenuItem mReset = new JMenuItem("Reset Battle");
		mReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().begin();
			}
		});
		
		add(mReset);
	}
}
