package com.mangolion.epicmangorpg.frames;

import java.awt.EventQueue;
import java.awt.Point;

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
import java.awt.Font;

public class FrameStepInfo extends JInternalFrame {

	public Step step;
	JTextArea lblDesc;
	JLabel  lblTime, lblCost, lblDamage, lblParryChance, lblBlockChance, lblDodgeChance;
	private JLabel lblProficiency;
	private JLabel lblCp;
	private JButton button;
	public FrameStepInfo(final Step step) {
		super("", true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		this.step = step;
		setBounds(100, 100, 346, 305);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		getContentPane().setLayout(null);
		
		lblDesc = new 	JTextArea("Desc:");
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDesc.setEditable(false);
		lblDesc.setLineWrap(true);
		lblDesc.setBounds(10, 11, 312, 70);
		getContentPane().add(lblDesc);
		
		 lblTime = new JLabel("Time:");
		 lblTime.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTime.setBounds(10, 92, 312, 14);
		getContentPane().add(lblTime);
		
		 lblCost = new JLabel("Costs:");
		 lblCost.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCost.setBounds(10, 117, 312, 14);
		getContentPane().add(lblCost);
		
		 lblDamage = new JLabel("Damage:");
		 lblDamage.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDamage.setBounds(10, 142, 312, 14);
		getContentPane().add(lblDamage);
		
		lblParryChance = new JLabel("Parry Chance:");
		lblParryChance.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblParryChance.setBounds(10, 167, 312, 14);
		getContentPane().add(lblParryChance);
		
		lblBlockChance = new JLabel("Block Chance:");
		lblBlockChance.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblBlockChance.setBounds(10, 192, 310, 14);
		getContentPane().add(lblBlockChance);
		
		lblDodgeChance = new JLabel("Dodge Chance:");
		lblDodgeChance.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDodgeChance.setBounds(10, 217, 310, 14);
		getContentPane().add(lblDodgeChance);
		
		lblProficiency = new JLabel("Proficiency:");
		lblProficiency.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblProficiency.setBounds(10, 242, 312, 14);
		getContentPane().add(lblProficiency);
		
		lblCp = new JLabel("CP:");
		lblCp.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCp.setBounds(153, 242, 167, 14);
		getContentPane().add(lblCp);
		
		button = new JButton("View Stat Buffs");
		button.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameBuffInfo(step, step.prof);
			}
		});
		button.setBounds(184, 138, 138, 23);
		getContentPane().add(button);
		refresh();
		super.moveToFront();
	}
	
	@Override
	public void moveToFront() {
	}
	
	public void refresh(){
		setTitle(step.name);
		lblDesc.setText(step.desc);
		lblTime.setText("Time: [Load - " + step.getLoadTime() + "] [Execute - " + step.getExecutionTime() + "] [Cooldown - " + step.getCooldownTime() + "]");
		lblCost.setText("Cost: hp - " + step.getHpCost() + " mp - " + step.getMpCost() + " sp - "+ step.getStamCost() + " bal - " + step.getBalCost());
		lblDamage.setText("Damage: " + step.getDamage() + "(" + step.getDmgPercent()*100 + "%)");
		lblParryChance.setText("Parry Chance: " + step.chanceParry*100 + "%");
		lblBlockChance.setText("Block Chance: " + step.chanceBlock*100 + "%");
		lblDodgeChance.setText("Dodge Chance: " + step.chanceDodge*100 + "%");
		lblProficiency.setText("Prof: " + step.prof*100 + "%");
		lblCp.setText("CP: " +step.getCP());
	}
}
