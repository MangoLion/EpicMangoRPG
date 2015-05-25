package com.mangolion.epicmangorpg.frames;

import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.commands.CommandHandler;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.weapons.Weapons;

import javax.swing.ListModel;

public class FrameSkills extends JDialog {

	Character character;
	CommandHandler handler;
	String result = "";
	JList<Skill> listCombat, listRecent, listAlchemy, listBow, listGun, listSword, listMagic;
	DefaultListModel<Skill>mCombat = new DefaultListModel<Skill>()
			, mRecent = new DefaultListModel<Skill>()
			, mAlchemy = new DefaultListModel<Skill>()
			, mBow = new DefaultListModel<Skill>()
			, mGun = new DefaultListModel<Skill>()
			, mSword = new DefaultListModel<Skill>()
			, mMagic = new DefaultListModel<Skill>();
	/**
	 * Create the frame.
	 */
	public FrameSkills(Character character) {
		setModal(true);
		this.character = character;
		setBounds(100, 100, 857, 448);
		
		Point pt = MouseInfo.getPointerInfo().getLocation();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 200, 192);
		getContentPane().add(scrollPane);
		
		JLabel lblCombat = new JLabel("Combat");
		lblCombat.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCombat.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblCombat);
		
		 listCombat = new JList<Skill>(mCombat);
		 listCombat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2){
						result = listCombat.getSelectedValue().name;
						setVisible(false);
						dispose();
					}
					super.mouseClicked(e);
				}
			});
		scrollPane.setViewportView(listCombat);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(220, 10, 200, 192);
		getContentPane().add(scrollPane_1);
		
		JLabel lblRecent = new JLabel("Recent");
		lblRecent.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecent.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		scrollPane_1.setColumnHeaderView(lblRecent);
		
		listRecent = new JList<Skill>(mRecent);
		listRecent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					result = listRecent.getSelectedValue().name;
					setVisible(false);
					dispose();
				}
				super.mouseClicked(e);
			}
		});
		scrollPane_1.setViewportView(listRecent);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(430, 10, 200, 192);
		getContentPane().add(scrollPane_2);
		
		JLabel lblSwords = new JLabel("Swords");
		lblSwords.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwords.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		scrollPane_2.setColumnHeaderView(lblSwords);
		
		listSword = new JList<Skill>(mSword);
		listSword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					result = listSword.getSelectedValue().name;
					setVisible(false);
					dispose();
				}
				super.mouseClicked(e);
			}
		});
		scrollPane_2.setViewportView(listSword);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(430, 213, 200, 192);
		getContentPane().add(scrollPane_3);
		
		JLabel lblAlchemy = new JLabel("Alchemy");
		lblAlchemy.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlchemy.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		scrollPane_3.setColumnHeaderView(lblAlchemy);
		
		listAlchemy = new JList<Skill>(mAlchemy);
		listAlchemy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					result = listAlchemy.getSelectedValue().name;
					setVisible(false);
					dispose();
				}
				super.mouseClicked(e);
			}
		});
		scrollPane_3.setViewportView(listAlchemy);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(220, 213, 200, 192);
		getContentPane().add(scrollPane_4);
		
		JLabel lblBows = new JLabel("Bows");
		lblBows.setHorizontalAlignment(SwingConstants.CENTER);
		lblBows.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		scrollPane_4.setColumnHeaderView(lblBows);
		
		 listBow = new JList<Skill>(mBow);
		scrollPane_4.setViewportView(listBow);
		listBow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					result = listBow.getSelectedValue().name;
					setVisible(false);
					dispose();
				}
				super.mouseClicked(e);
			}
		});
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 213, 200, 192);
		getContentPane().add(scrollPane_5);
		
		JLabel lblGuns = new JLabel("Guns");
		lblGuns.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuns.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		scrollPane_5.setColumnHeaderView(lblGuns);
		
		listGun = new JList<Skill>(mGun);
		listGun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					result = listGun.getSelectedValue().name;
					setVisible(false);
					dispose();
				}
				super.mouseClicked(e);
			}
		});
		scrollPane_5.setViewportView(listGun);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(640, 10, 200, 192);
		getContentPane().add(scrollPane_6);
		
		JLabel lblMagic = new JLabel("Magic");
		lblMagic.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_6.setColumnHeaderView(lblMagic);
		
		listMagic = new JList<Skill>(mMagic);
		listMagic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					result = listMagic.getSelectedValue().name;
					setVisible(false);
					dispose();
				}
				super.mouseClicked(e);
			}
		});
		scrollPane_6.setViewportView(listMagic);
		refresh();
	}
	
	public void refresh(){
		for (Skill skill : character.skillsRecent)
			mRecent.addElement(skill);
		for (Skill skill: character.skills){
			if (skill.weapons.getFirst() == Weapons.ALL){
				mCombat.addElement(skill);
				continue;
			}
			if (skill.checkWeapon(Weapons.Bladed))
				mSword.addElement(skill);
			if (skill.checkWeapon(Weapons.Bow))
				mBow.addElement(skill);
			if (skill.checkWeapon(Weapons.Gun) || skill.checkWeapon(Weapons.LauncherGrenade)||skill.checkWeapon(Weapons.Reloadable) )
				mGun.addElement(skill);
			if (skill.checkWeapon(Weapons.Cylinder))
				mAlchemy.addElement(skill);
			if (skill.type == ActionType.Magic)
				mMagic.addElement(skill);
		}
	}
	
	public String showDialog(){
		setVisible(true);
			return result;
}
}
