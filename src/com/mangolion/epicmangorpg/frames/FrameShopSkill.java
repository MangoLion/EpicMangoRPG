package com.mangolion.epicmangorpg.frames;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.floors.FloorTrainning;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.Weapon;

public class FrameShopSkill extends JInternalFrame {

	public Skill skill;
	
	JList<Step>listSteps;
	DefaultListModel<Step>modelSteps = new DefaultListModel<Step>();
	JTextArea lblDesc;
	JLabel lblTotalTime, lblCost, lblTotalDamage;
	private JLabel lblName;
	private JScrollPane scrollPane;
	private JList<Skill> listSkills;
	DefaultListModel<Skill> model = new DefaultListModel<Skill>();
	private JLabel lblSkills, lblAoe, lblPrice;
	private JButton btnBuySession;
	public FrameShopSkill(String title, final Weapon weapon, LinkedList<Skill> skills, final Item ammo) {
		super(title, false, true, true, false);
		skills.removeLast();
		FrameGame.getInstance().addFrame(this);
		setSize(591, 490);
		Point pt = FrameGame.getInstance().getMousePos();
		//setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		getContentPane().setLayout(null);
		
		lblDesc = new 	JTextArea("Desc:");
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDesc.setEditable(false);
		lblDesc.setLineWrap(true);
		lblDesc.setBounds(256, 68, 312, 70);
		getContentPane().add(lblDesc);
		
		lblTotalTime = new JLabel("Total Time:");
		lblTotalTime.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTotalTime.setBounds(256, 149, 144, 14);
		getContentPane().add(lblTotalTime);
		
		lblCost = new JLabel("Costs:");
		lblCost.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCost.setBounds(256, 174, 312, 14);
		getContentPane().add(lblCost);
		
		lblTotalDamage = new JLabel("Total Damage:");
		lblTotalDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTotalDamage.setBounds(256, 199, 312, 14);
		getContentPane().add(lblTotalDamage);
		
		JScrollPane scrollSteps = new JScrollPane();
		scrollSteps.setBounds(256, 254, 312, 158);
		getContentPane().add(scrollSteps);
		
		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblSteps.setHorizontalAlignment(SwingConstants.CENTER);
		scrollSteps.setColumnHeaderView(lblSteps);
		
		listSteps = new JList<Step>(modelSteps);
		listSteps.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		listSteps.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						FrameStepInfo info = new FrameStepInfo(listSteps.getSelectedValue());
						info.setVisible(true);
				}
				super.mouseClicked(e);
			}
		});
		scrollSteps.setViewportView(listSteps);
		
		JButton btnViewStatBuffs = new JButton("View Stat Buffs");
		btnViewStatBuffs.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewStatBuffs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrameBuffInfo(skill, 0.01f);
			}
		});
		btnViewStatBuffs.setBounds(406, 195, 160, 23);
		getContentPane().add(btnViewStatBuffs);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblName.setBounds(256, 11, 312, 46);
		getContentPane().add(lblName);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 236, 430);
		getContentPane().add(scrollPane);
		
		listSkills = new JList<Skill>(model);
		listSkills.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		listSkills.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				skill = listSkills.getSelectedValue();
				refresh();
				super.mouseClicked(e);
			}
		});
		scrollPane.setViewportView(listSkills);
		
		lblSkills = new JLabel("Skills");
		lblSkills.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblSkills.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblSkills);
		
		btnBuySession = new JButton("Buy Session");
		btnBuySession.setBounds(358, 418, 91, 23);
		btnBuySession.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				float cost = skill.shopPrice;
				if (CharacterPlayer.instance.getCrystals() >= cost){
					CharacterPlayer.instance.changeCrystal(-cost);
					Game.getInstance().begin(new FloorTrainning(listSkills.getSelectedValue(), weapon, ammo));					
				}
			}
		});
		getContentPane().add(btnBuySession);
		
		 lblAoe = new JLabel("AOE:");
		lblAoe.setBounds(406, 150, 162, 14);
		getContentPane().add(lblAoe);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(256, 224, 310, 14);
		getContentPane().add(lblPrice);
		
		skill = skills.getFirst();
		for (Skill skill: skills)
		model.addElement(skill);
		refresh();
		super.moveToFront();
	}
	
	@Override
	public void moveToFront() {
	}
	
	public void refresh(){
		lblName.setText(skill.name);
		lblAoe.setText("AOE: " + skill.steps.getFirst().isAOE);
		lblPrice.setText("Price: " + skill.shopPrice +  " crystals");
		lblDesc.setText( skill.desc);
		lblTotalTime.setText("Total Time: " + skill.getTotalTime() + " seconds");
		lblCost.setText("Cost: [hp - " + skill.getTotalHPCost() + "] [mp - " + skill.getTotalMPCost() + "] [sp - "+ skill.getTotalSPCost() + "] [bal - " +skill.getTotalBalCost()+"]");
		lblTotalDamage.setText("Damage: " + skill.getTotalDamage() + "(" + skill.getTotalDamagePercent()*100 + "%)");
		modelSteps.clear();
		for (Step step: skill.steps)
			modelSteps.addElement(step);
	}
}
