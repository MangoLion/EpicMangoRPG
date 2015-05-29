package com.mangolion.epicmangorpg.components;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.frames.FrameGameInfo;
import com.mangolion.epicmangorpg.game.Game;

public class MenuHelp extends JMenu {
	public MenuHelp() {
		super("Help");
		
		JMenuItem mInfo = new JMenuItem("Wiki");
		mInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Desktop d=Desktop.getDesktop();

				 // Browse a URL, say google.com
				 try {
					d.browse(new URI("https://github.com/MangoLion/EpicMangoRPG/wiki"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		add(mInfo);
	}
}
