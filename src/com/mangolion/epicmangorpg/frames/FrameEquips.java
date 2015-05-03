package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.weapons.Armor;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Barehands;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrameEquips extends JInternalFrame {

	private JPanel contentPane;

	Character character;
	JLabel lblHead, lblBody, lblLegs, lblFeet, lblRobes, lblWeapon, lblAccessories, lblHands; 
	JButton btnViewHead, btnEquipHead, btnViewBody, btnEquipBody, btnViewFeet, btnEquipFeet, btnViewRobe, btnEquipRobe, btnUnequipAll, btnViewWeapon,btnEquipWeapon, btnEquipHands, btnViewHands, btnEquipAcc, btnViewAcc,
	btnViewLegs, btnEquipLegs;
	/**
	 * Create the frame.
	 */
	public FrameEquips(final Character character) {
		super("", true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		this.character = character;
		boolean isPlayer = (character == CharacterPlayer.instance);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 321, 319);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHead = new JLabel("Head:");
		lblHead.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblHead.setBounds(10, 11, 148, 14);
		contentPane.add(lblHead);
		
		btnViewHead = new JButton("View");
		btnViewHead.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewHead.setBounds(156, 11, 61, 23);
		contentPane.add(btnViewHead);
		
		btnEquipHead = new JButton("Unequip");
		btnEquipHead.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipHead.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.unequipArmor(Armor.HEAD);
				refresh();
			}
		});
		btnEquipHead.setBounds(221, 12, 79, 23);
		contentPane.add(btnEquipHead);
		
		lblBody = new JLabel("Body:");
		lblBody.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblBody.setBounds(10, 36, 148, 14);
		contentPane.add(lblBody);
		
		lblLegs = new JLabel("Pant:");
		lblLegs.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblLegs.setBounds(10, 59, 148, 14);
		contentPane.add(lblLegs);
		
		lblFeet = new JLabel("Feet:");
		lblFeet.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblFeet.setBounds(10, 90, 148, 14);
		contentPane.add(lblFeet);
		
		lblRobes = new JLabel("Robe:");
		lblRobes.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblRobes.setBounds(10, 115, 148, 14);
		contentPane.add(lblRobes);
		
		btnViewBody = new JButton("View");
		btnViewBody.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewBody.setBounds(156, 36, 61, 23);
		contentPane.add(btnViewBody);
		
		btnEquipBody = new JButton("Unequip");
		btnEquipBody.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipBody.setBounds(221, 37, 79, 23);
		btnEquipBody.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.unequipArmor(Armor.BODY);
				refresh();
			}
		});
		contentPane.add(btnEquipBody);
		
		 btnViewFeet = new JButton("View");
		 btnViewFeet.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewFeet.setBounds(156, 85, 61, 23);
		btnViewFeet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Armor armor = character.feet;
				if (armor != null)
					new FrameArmorView(armor, character);
			}
		});
		contentPane.add(btnViewFeet);
		
		btnEquipFeet = new JButton("Unequip");
		btnEquipFeet.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipFeet.setBounds(221, 86, 79, 23);
		btnEquipFeet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.unequipArmor(Armor.FEET);
				refresh();
			}
		});
		contentPane.add(btnEquipFeet);
		
		btnViewRobe = new JButton("View");
		btnViewRobe.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewRobe.setBounds(156, 110, 61, 23);
		btnViewRobe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Armor armor = character.robe;
				if (armor != null)
					new FrameArmorView(armor, character);
			}
		});
		contentPane.add(btnViewRobe);
		
		btnEquipRobe = new JButton("Unequip Robe");
		btnEquipRobe.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipRobe.setBounds(221, 111, 79, 23);
		btnEquipRobe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.unequipArmor(Armor.ROBE);
				refresh();
			}
		});
		contentPane.add(btnEquipRobe);
		
		btnUnequipAll = new JButton("Unequip all");
		btnUnequipAll.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnUnequipAll.setBounds(86, 234, 120, 23);
		contentPane.add(btnUnequipAll);
		
		lblWeapon = new JLabel("Weapon: ");
		lblWeapon.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblWeapon.setBounds(10, 208, 153, 14);
		contentPane.add(lblWeapon);
		
		btnViewWeapon = new JButton("View");
		btnViewWeapon.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewWeapon.setBounds(156, 199, 61, 23);
		btnViewWeapon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					new FrameWeaponView(character.weapon, character);
			}
		});
		contentPane.add(btnViewWeapon);
		
		btnEquipWeapon = new JButton("Unequip");
		btnEquipWeapon.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipWeapon.setBounds(221, 200, 79, 23);
		btnEquipWeapon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.weapon = new Barehands();
				refresh();
			}
		});
		
		contentPane.add(btnEquipWeapon);
		
		btnViewLegs = new JButton("View");
		btnViewLegs.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewLegs.setBounds(156, 55, 61, 23);
		btnViewLegs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Armor armor = character.legs;
				if (armor != null)
					new FrameArmorView(armor, character);
			}
		});
		contentPane.add(btnViewLegs);
		
		btnEquipLegs = new JButton("Unequip");
		btnEquipLegs.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipLegs.setBounds(221, 56, 79, 23);
		btnEquipLegs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.unequipArmor(Armor.LEG);
				refresh();
			}
		});
		contentPane.add(btnEquipLegs);
		
		 lblAccessories = new JLabel("Accessories:");
		lblAccessories.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblAccessories.setBounds(10, 182, 153, 14);
		contentPane.add(lblAccessories);
		
		btnViewAcc = new JButton("View");
		btnViewAcc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewAcc.setBounds(156, 173, 61, 23);
		btnViewAcc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Armor armor = character.accessory;
				if (armor != null)
					new FrameArmorView(armor, character);
			}
		});
		contentPane.add(btnViewAcc);
		
		btnEquipAcc = new JButton("Unequip");
		btnEquipAcc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipAcc.setBounds(221, 174, 79, 23);
		btnEquipAcc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.unequipArmor(Armor.ACCESSORY);
				refresh();
			}
		});
		contentPane.add(btnEquipAcc);
		
		lblHands = new JLabel("Hands:");
		lblHands.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblHands.setBounds(10, 156, 153, 14);
		contentPane.add(lblHands);
		
		btnViewHands = new JButton("View");
		btnViewHands.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewHands.setBounds(156, 147, 61, 23);
		btnViewHands.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Armor armor = character.hands;
				if (armor != null)
					new FrameArmorView(armor, character);
			}
		});
		contentPane.add(btnViewHands);
		
		btnEquipHands = new JButton("Unequip");
		btnEquipHands.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnEquipHands.setBounds(221, 148, 79, 23);
		btnEquipHands.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				character.unequipArmor(Armor.HAND);
				refresh();
			}
		});
		contentPane.add(btnEquipHands);
		
		btnEquipAcc.setEnabled(isPlayer);
		btnEquipBody.setEnabled(isPlayer);
		btnEquipFeet.setEnabled(isPlayer);
		btnEquipHands.setEnabled(isPlayer);
		btnEquipHead.setEnabled(isPlayer);
		btnEquipLegs.setEnabled(isPlayer);
		btnEquipRobe.setEnabled(isPlayer);
		btnEquipWeapon.setEnabled(isPlayer);
		
		refresh();
	}
	
	public void refresh(){
		Armor armor;
		String str = "Head: ";
		armor = character.head;
		if (armor != null)
			str += armor.name;
		lblHead.setText(str);
		
		str = "Body: ";
		armor = character.body;
		if (armor != null)
			str += armor.name;
		lblBody.setText(str);
		
		str = "Legs: ";
		armor = character.legs;
		if (armor != null)
			str += armor.name;
		lblLegs.setText(str);
		
		str = "Feet: ";
		armor = character.feet;
		if (armor != null)
			str += armor.name;
		lblFeet.setText(str);
		
		str = "Hands: ";
		armor = character.hands;
		if (armor != null)
			str += armor.name;
		lblHands.setText(str);
		
		str = "Robe: ";
		armor = character.robe;
		if (armor != null)
			str += armor.name;
		lblRobes.setText(str);
		
		str = "Accessories: ";
		armor = character.accessory;
		if (armor != null)
			str += armor.name;
		lblAccessories.setText(str);
		
		Weapon weapon;
		str = "Weapon: ";
		weapon = character.weapon;
		if (weapon != null)
			str += weapon.name;
		lblWeapon.setText(str);
		super.moveToFront();
	}
	
	@Override
	public void moveToFront() {
	}
}
