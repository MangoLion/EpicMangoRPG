package com.mangolion.epicmangorpg.frames;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mangolion.epicmangorpg.game.Profile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelProfile extends JPanel {
	Profile profile;
	PanelProfile self = this;
	public boolean isSelected = false;
	public void refresh(){
		lblName.setText(profile.getName());
		lblCurrentFloor.setText("Current Floor: " + profile.currentFloor);
		lblFloorProgress.setText("Floor Progress: " + profile.getName());
	}
	JLabel lblName, lblTimePlayed, lblRace, lblCurrentFloor, lblFloorProgress;
	public PanelProfile(Profile profile, final FrameLoadProfile load) {
		setPreferredSize(new Dimension(300, 200));
		this.profile = profile;
		setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblName.setBounds(10, 11, 278, 54);
		add(lblName);
		
		lblTimePlayed = new JLabel("Time Played:");
		lblTimePlayed.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTimePlayed.setBounds(10, 76, 278, 14);
		add(lblTimePlayed);
		
		lblRace = new JLabel("Race:");
		lblRace.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblRace.setBounds(10, 101, 278, 14);
		add(lblRace);
		
		lblCurrentFloor = new JLabel("Current Floor:");
		lblCurrentFloor.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCurrentFloor.setBounds(10, 126, 278, 14);
		add(lblCurrentFloor);
		
		lblFloorProgress = new JLabel("Floor Progress:");
		lblFloorProgress.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblFloorProgress.setBounds(10, 151, 288, 14);
		add(lblFloorProgress);
		refresh();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSelected(true);
				load.setSelected(self);
				super.mouseClicked(e);
			}
		});

	}
	public void setSelected(boolean isSelected2) {
		isSelected = isSelected2;
		if (isSelected2)
			setBorder((new JTextField()).getBorder());//BorderFactory.createLineBorder(Color.white) );
		else
			setBorder(null);
	}

}
