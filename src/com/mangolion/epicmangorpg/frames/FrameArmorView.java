package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.weapons.Armor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameArmorView extends JInternalFrame {

	private JPanel contentPane;

	Armor armor;
	Character character;

	/**
	 * Create the frame.
	 */
	public FrameArmorView(final	Armor armor, final Character character) {
		super(armor.name, true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		
		this.armor = armor;
		this.character = character;
		boolean isPlayer = (character == CharacterPlayer.instance);
		setBounds(100, 100, 438, 264);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane tfDesc = new JTextPane();
		tfDesc.setBounds(10, 11, 402, 103);
		contentPane.add(tfDesc);
		
		JLabel lblDurability = new JLabel("Durability:");
		lblDurability.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblDurability.setBounds(10, 125, 271, 14);
		contentPane.add(lblDurability);
		
		JLabel lblDef = new JLabel("Defense Bonus:");
		lblDef.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblDef.setBounds(10, 150, 271, 14);
		contentPane.add(lblDef);
		
		JLabel lblAgi = new JLabel("Agility Bonus/Penalty:");
		lblAgi.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblAgi.setBounds(10, 175, 271, 14);
		contentPane.add(lblAgi);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblType.setBounds(10, 200, 271, 14);
		contentPane.add(lblType);
		
		JButton btnEquip = new JButton("Equip");
		btnEquip.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquip.setBounds(262, 125, 150, 23);
		btnEquip.setEnabled(isPlayer);
		btnEquip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.equip(armor);
				dispose();
			}
		});
		contentPane.add(btnEquip);
		
		JButton btnViewStatBuffs = new JButton("View Stat Buffs");
		btnViewStatBuffs.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewStatBuffs.setBounds(262, 166, 150, 23);
		contentPane.add(btnViewStatBuffs);
		
		tfDesc.setText(armor.getDesc());
		
		lblDurability.setText("Durability: " + armor.durability);
		lblDef.setText("Defense Bonus: " + armor.getDefBuff());
		lblAgi.setText("Agi Bonus/Penalty: " + armor.getAgiBuff());
		//lblType.setText("Type: " + armor.);
		super.moveToFront();
	}
	
	@Override
	public void moveToFront() {
	}
	
}
