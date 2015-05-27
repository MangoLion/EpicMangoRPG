package com.mangolion.epicmangorpg.frames;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import com.mangolion.epicmangorpg.components.Barrier;

import java.awt.Font;

public class FrameBarrierInfo extends JInternalFrame {


	public FrameBarrierInfo(Barrier barrier) {
		super(barrier.name, true, true, true, true);
		setBounds(100, 100, 358, 230);
		getContentPane().setLayout(null);
		FrameGame.getInstance().addFrame(this);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 324, 72);
		getContentPane().add(scrollPane);
		
		JTextPane tfDesc = new JTextPane();
		scrollPane.setViewportView(tfDesc);
		
		JLabel lblHp = new JLabel("Hp:");
		lblHp.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblHp.setBounds(10, 94, 169, 14);
		getContentPane().add(lblHp);
		lblHp.setText("Hp: " + barrier.hp);
		
		JLabel lblDef = new JLabel("Physical Def:");
		lblDef.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblDef.setBounds(10, 117, 169, 14);
		getContentPane().add(lblDef);
		lblDef.setText("Physical Def: " + barrier.def);
		
		JLabel lblMagicDef = new JLabel("Magic Def:");
		lblMagicDef.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblMagicDef.setBounds(165, 117, 169, 14);
		getContentPane().add(lblMagicDef);
		lblMagicDef.setText("Magic Def: " + barrier.magicDef);
		
		JLabel lbType = new JLabel("Type:");
		lbType.setBounds(10, 167, 232, 14);
		getContentPane().add(lbType);
		String type;
		switch (barrier.type) {
		case Barrier.ALL:
			type = "Magic, Melee and Range";
			break;
		case Barrier.MAGIC:
			type = "Magic";
			break;
		case Barrier.MELEE:
			type = "Melee";
			break;
		case Barrier.RANGE:
			type = "Range";
			break;
		default:
			type = "N/A";
			break;
		}
		lbType.setText("Type: " + type);
		
		JLabel lblProt = new JLabel("Protection:");
		lblProt.setText("Protection: " + barrier.prot);
		
		lblProt.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblProt.setBounds(10, 142, 169, 14);
		getContentPane().add(lblProt);
		
		JLabel lblAbsorbPercent = new JLabel("Absorb Percent:" + barrier.absorbPercent*100+ "%");
		lblAbsorbPercent.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblAbsorbPercent.setBounds(165, 142, 169, 14);
		getContentPane().add(lblAbsorbPercent);
		
		JLabel lblTime = new JLabel("Time:" + barrier.time + " seconds");
		lblTime.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblTime.setBounds(165, 166, 169, 14);
		getContentPane().add(lblTime);
		super.moveToFront();
	}
}
