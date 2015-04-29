package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.Themes;
import com.mangolion.epicmangorpg.components.WrapLayout;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Profile;
import com.mangolion.epicmangorpg.game.WiniWriter;

import javax.swing.BoxLayout;

import java.awt.Font;

public class FrameLoadProfile extends JFrame {

	private JPanel contentPane;

	JPanel panelProfiles;

	public FrameLoadProfile() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 805, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		panelProfiles = new JPanel(new WrapLayout());
		JScrollPane scrollPane = new JScrollPane(panelProfiles);
		contentPane.add(scrollPane);

		JLabel lblProfiles = new JLabel("Profiles");
		lblProfiles.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblProfiles.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblProfiles);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150, 200));
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(null);

		JButton button = new JButton("Load Profile");
		button.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Component component : panelProfiles.getComponents()) {
					if (((PanelProfile) component).isSelected) {
						//Game game = new Game();
						Game.getInstance().loadGame(((PanelProfile) component).profile);
						FrameGame.start();
						dispose();
					}
				}
			}
		});
		button.setBounds(10, 154, 130, 23);
		panel.add(button);

		JButton button_1 = new JButton("Delete Profile");
		button_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Component component : panelProfiles.getComponents()) {
					if (((PanelProfile) component).isSelected) {
						WiniWriter.deleteProfile(((PanelProfile) component).profile.getName());
						panelProfiles.remove(component);
						panelProfiles.revalidate();
						panelProfiles.repaint();
					}
				}
			
			
			}
		});
		button_1.setBounds(10, 188, 130, 23);
		panel.add(button_1);

		JButton button_2 = new JButton("New Profile");
		button_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter name: ");
				if (name == null)
					return;
				CharacterPlayer player = new CharacterPlayer(name);
				player.init();
				Profile profile = new Profile(player, 0, 0, true, false, Themes.getCurrentTheme());
				WiniWriter.saveProfile(profile);
				
				FrameGame.start();
				
				dispose();
			}
		});
		button_2.setBounds(12, 219, 128, 23);
		panel.add(button_2);

		
		refresh();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FrameMainMenu mainMenu = new FrameMainMenu();
				mainMenu.setVisible(true);
				super.windowClosing(e);
			}
		});
	}
	
	public void refresh(){
		panelProfiles.removeAll();
		for (String name : FrameGame.profileFile.list()) {
			Profile profile = WiniWriter.readProfile(name);
			if (profile == null)
				continue;
			panelProfiles.add(new PanelProfile(profile, this));
		}
	}

	public void setSelected(PanelProfile self) {
		for (Component component : panelProfiles.getComponents())
			if (component != self)
				((PanelProfile) component).setSelected(false);
	}
}
