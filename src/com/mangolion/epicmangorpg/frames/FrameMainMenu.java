package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.Themes;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Profile;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.WiniWriter;

public class FrameMainMenu extends JFrame {

	private JPanel contentPane;
	public static File infoFile = new File(
			System.getProperty("user.dir") + "\\\\" + "gameinfo.txt"),
			profileFile = new File(
					System.getProperty("user.dir") + "\\\\" + "profiles");;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new StylePainter();
		if (!profileFile.exists())
			profileFile.mkdirs();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMainMenu frame = new FrameMainMenu();
					Themes.setTheme("Dust Coffee");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameMainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEpicMangoAdventure = new JLabel("Epic Mango Adventure");
		lblEpicMangoAdventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblEpicMangoAdventure.setFont(new Font("Comic Sans MS", Font.PLAIN, 58));
		lblEpicMangoAdventure.setBounds(0, 11, 641, 146);
		contentPane.add(lblEpicMangoAdventure);
		
		JButton btnNewProfile = new JButton("New Profile");
		btnNewProfile.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewProfile.setBounds(10, 168, 144, 36);
		btnNewProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter name: ");
				CharacterPlayer player = new CharacterPlayer(name);
				Profile profile = new Profile(player, 0, 0, true, false, Themes.getCurrentTheme());
				WiniWriter.saveProfile(profile);
				player.init();
				//Game game = new Game();
				//game.loadGame(profile);
				FrameGame.start();
				dispose();
			}
		});
		contentPane.add(btnNewProfile);
		
		JButton btnLoadProfile = new JButton("Load Profile");
		btnLoadProfile.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnLoadProfile.setBounds(155, 168, 144, 36);
		btnLoadProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameLoadProfile loadProfile = new FrameLoadProfile();
				loadProfile.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnLoadProfile);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnOptions.setBounds(300, 168, 144, 36);
		contentPane.add(btnOptions);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnCredits.setBounds(366, 204, 144, 36);
		contentPane.add(btnCredits);
		
		JButton btnViewEverything = new JButton("View Everything");
		btnViewEverything.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnViewEverything.setBounds(444, 168, 173, 36);
		contentPane.add(btnViewEverything);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnHelp.setBounds(87, 204, 144, 36);
		contentPane.add(btnHelp);
	}
}
