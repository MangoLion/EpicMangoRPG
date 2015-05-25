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
import com.mangolion.epicmangorpg.weapons.Weapon;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameWeaponView extends JInternalFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FrameWeaponView(final Weapon weapon, final Character character) {
		super(weapon.name, true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		setBounds(100, 100, 438, 503);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		boolean isPlayer = (character == CharacterPlayer.instance);
		JTextPane tfDesc = new JTextPane();
		tfDesc.setBounds(10, 11, 402, 103);
		tfDesc.setText(weapon.getDesc());
		contentPane.add(tfDesc);
		
		JLabel lblDurability = new JLabel("Durability: " + weapon.durability + "/" + weapon.maxDurability );
		lblDurability.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblDurability.setBounds(10, 125, 271, 14);
		contentPane.add(lblDurability);
		
		JLabel lblBaseDamage = new JLabel("Base Damage: " + weapon.baseDamage);
		lblBaseDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblBaseDamage.setBounds(10, 150, 271, 14);
		contentPane.add(lblBaseDamage);
		
		JLabel lblBaseMagicDamage = new JLabel("Base Magic Damage: " + weapon.magicDamage);
		lblBaseMagicDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblBaseMagicDamage.setBounds(10, 175, 271, 14);
		contentPane.add(lblBaseMagicDamage);
		
		JLabel lblType = new JLabel("Type: ");
		lblType.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblType.setBounds(10, 200, 271, 14);
		contentPane.add(lblType);
		
		JButton btnEquip = new JButton("Equip");
		btnEquip.setEnabled(isPlayer);
		btnEquip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.equip(weapon);
				dispose();
			}
		});
		btnEquip.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquip.setBounds(267, 125, 145, 23);
		contentPane.add(btnEquip);
		
		JLabel lblMeleeSpeedModifier = new JLabel("Melee Speed Modifier: " + weapon.speedModifier*100 + "%");
		lblMeleeSpeedModifier.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblMeleeSpeedModifier.setBounds(10, 225, 271, 14);
		contentPane.add(lblMeleeSpeedModifier);
		
		JLabel lblMagicSpeedModifier = new JLabel("Magic Speed Modifier: ");
		lblMagicSpeedModifier.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblMagicSpeedModifier.setBounds(10, 255, 271, 14);
		contentPane.add(lblMagicSpeedModifier);
		
		JButton btnViewStatBuffs = new JButton("View Stat Buffs");
		btnViewStatBuffs.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewStatBuffs.setBounds(267, 166, 145, 23);
		btnViewStatBuffs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FrameBuffInfo(weapon, 1).setVisible(true);
			}
		});
		contentPane.add(btnViewStatBuffs);
		
		JLabel lblGunDamage = new JLabel("Gun Damage: " + weapon.gunDamage);
		lblGunDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblGunDamage.setBounds(10, 280, 271, 14);
		contentPane.add(lblGunDamage);
		
		JLabel lblMagicDamage = new JLabel("Magic Damage: " + weapon.magicDamage);
		lblMagicDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblMagicDamage.setBounds(10, 305, 271, 14);
		contentPane.add(lblMagicDamage);
		
		JLabel lblAlchemyDamage = new JLabel("Alchemy Damage: " + weapon.alchemyDamage);
		lblAlchemyDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblAlchemyDamage.setBounds(10, 335, 271, 14);
		contentPane.add(lblAlchemyDamage);
		
		JLabel lblBowDamage = new JLabel("Bow Damage: " + weapon.bowDamage);
		lblBowDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblBowDamage.setBounds(10, 360, 271, 14);
		contentPane.add(lblBowDamage);
		
		JLabel lblMaxAmmo = new JLabel("Max Ammo: " + weapon.maxAmmo);
		lblMaxAmmo.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblMaxAmmo.setBounds(10, 385, 207, 14);
		contentPane.add(lblMaxAmmo);
		
		JLabel lblFireRateOn = new JLabel("Fire rate on Automatic: " + weapon.fireRate);
		lblFireRateOn.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblFireRateOn.setBounds(10, 410, 207, 14);
		contentPane.add(lblFireRateOn);
		
		JLabel lblJamChance = new JLabel("Jam Chance: " + weapon.chanceJam*100 + "%");
		lblJamChance.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblJamChance.setBounds(10, 435, 207, 14);
		contentPane.add(lblJamChance);
	}
}
