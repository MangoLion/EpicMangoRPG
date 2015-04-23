package com.mangolion.epicmangorpg.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

import com.mangolion.epicmangorpg.steps.Step;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameStepInfo extends JFrame {

	public Step step;
	JTextArea lblDesc;
	JLabel  lblTime, lblCost, lblDamage, lblParryChance, lblBlockChance, lblDodgeChance;
	private JLabel lblProficiency;
	private JLabel lblCp;
	private JButton button;
	public FrameStepInfo(final Step step) {
		this.step = step;
		setBounds(100, 100, 346, 297);
		getContentPane().setLayout(null);
		
		lblDesc = new 	JTextArea("Desc:");
		lblDesc.setEditable(false);
		lblDesc.setLineWrap(true);
		lblDesc.setBounds(10, 11, 312, 70);
		getContentPane().add(lblDesc);
		
		 lblTime = new JLabel("Time:");
		lblTime.setBounds(10, 92, 312, 14);
		getContentPane().add(lblTime);
		
		 lblCost = new JLabel("Costs:");
		lblCost.setBounds(10, 117, 312, 14);
		getContentPane().add(lblCost);
		
		 lblDamage = new JLabel("Damage:");
		lblDamage.setBounds(10, 142, 312, 14);
		getContentPane().add(lblDamage);
		
		lblParryChance = new JLabel("Parry Chance:");
		lblParryChance.setBounds(10, 167, 312, 14);
		getContentPane().add(lblParryChance);
		
		lblBlockChance = new JLabel("Block Chance:");
		lblBlockChance.setBounds(10, 192, 310, 14);
		getContentPane().add(lblBlockChance);
		
		lblDodgeChance = new JLabel("Dodge Chance:");
		lblDodgeChance.setBounds(10, 217, 310, 14);
		getContentPane().add(lblDodgeChance);
		
		lblProficiency = new JLabel("Proficiency:");
		lblProficiency.setBounds(10, 242, 312, 14);
		getContentPane().add(lblProficiency);
		
		lblCp = new JLabel("CP:");
		lblCp.setBounds(153, 242, 167, 14);
		getContentPane().add(lblCp);
		
		button = new JButton("View Stat Buffs");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameBuffInfo(step, step.prof);
			}
		});
		button.setBounds(215, 138, 107, 23);
		getContentPane().add(button);
		refresh();
	}
	
	public void refresh(){
		setTitle(step.name);
		lblDesc.setText(step.desc);
		lblTime.setText("Time: [Load - " + step.getLoadTime() + "] [Execute - " + step.getExecutionTime() + "] [Cooldown - " + step.getCooldownTime() + "]");
		lblCost.setText("Cost: hp - " + step.hpCost + " mp - " + step.mpCost + " sp - "+ step.stamCost + " bal - " + step.balCost);
		lblDamage.setText("Damage: " + step.getDamage() + "(" + step.getDmgPercent() + "%)");
		lblParryChance.setText("Parry Chance: " + step.chanceParry*100 + "%");
		lblBlockChance.setText("Block Chance: " + step.chanceBlock*100 + "%");
		lblDodgeChance.setText("Dodge Chance: " + step.chanceDodge*100 + "%");
		lblProficiency.setText("Prof: " + step.prof*100 + "%");
		lblCp.setText("CP: " +step.getCP());
	}
}
