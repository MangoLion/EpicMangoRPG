package com.mangolion.epicmangorpg.frames;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.steps.Step;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrameSkillInfo extends JInternalFrame {

	public Skill skill;
	
	JList<Step>listSteps;
	DefaultListModel<Step>modelSteps = new DefaultListModel<Step>();
	JTextArea lblDesc;
	JLabel lblTotalTime, lblCost, lblTotalDamage, lblProficiency;
	private JLabel lblCp;
	public FrameSkillInfo(final Skill skill) {
		super("", true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		this.skill = skill;
		setSize(346, 456);
		Point pt = FrameGame.getInstance().getMousePos();
		if (pt != null)
			setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		getContentPane().setLayout(null);
		
		lblDesc = new 	JTextArea("Desc:");
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDesc.setEditable(false);
		lblDesc.setLineWrap(true);
		lblDesc.setBounds(10, 11, 312, 70);
		getContentPane().add(lblDesc);
		
		lblTotalTime = new JLabel("Total Time:");
		lblTotalTime.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTotalTime.setBounds(10, 92, 312, 14);
		getContentPane().add(lblTotalTime);
		
		lblCost = new JLabel("Costs:");
		lblCost.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCost.setBounds(10, 117, 312, 14);
		getContentPane().add(lblCost);
		
		lblTotalDamage = new JLabel("Total Damage:");
		lblTotalDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTotalDamage.setBounds(10, 142, 312, 14);
		getContentPane().add(lblTotalDamage);
		
		JScrollPane scrollSteps = new JScrollPane();
		scrollSteps.setBounds(10, 192, 312, 215);
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
		
		lblProficiency = new JLabel("Proficiency:");
		lblProficiency.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblProficiency.setBounds(10, 167, 312, 14);
		getContentPane().add(lblProficiency);
		
		lblCp = new JLabel("CP:");
		lblCp.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCp.setBounds(160, 167, 160, 14);
		getContentPane().add(lblCp);
		
		JButton btnViewStatBuffs = new JButton("View Stat Buffs");
		btnViewStatBuffs.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnViewStatBuffs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrameBuffInfo(skill, skill.getProf());
			}
		});
		btnViewStatBuffs.setBounds(160, 138, 160, 23);
		getContentPane().add(btnViewStatBuffs);
		refresh();
		super.moveToFront();
	}
	
	@Override
	public void moveToFront() {
	}
	
	public void refresh(){
		setTitle(skill.name);
		lblDesc.setText( skill.desc);
		lblTotalTime.setText("Total Time: " + skill.getTotalTime() + " seconds");
		lblCost.setText("Cost: [hp - " + skill.getTotalHPCost() + "] [mp - " + skill.getTotalMPCost() + "] [sp - "+ skill.getTotalSPCost() + "] [bal - " +skill.getTotalBalCost()+"]");
		lblTotalDamage.setText("Damage: " + skill.getTotalDamage() + "(" + skill.getTotalDamagePercent()*100 + "%)");
		lblCp.setText("CP: " + skill.getCP());
		lblProficiency.setText("Prof: "+ skill.getProf()*100 + "%" );
		modelSteps.clear();
		for (Step step: skill.steps)
			modelSteps.addElement(step);
	}
}
