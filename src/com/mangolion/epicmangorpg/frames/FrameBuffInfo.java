package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.mangolion.epicmangorpg.components.StatBuff;
import com.mangolion.epicmangorpg.game.Utility;

public class FrameBuffInfo extends JInternalFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public FrameBuffInfo(final StatBuff buff, final float prof) {
		super("", true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		setBounds(100, 100, 438, 302);
		Point pt = FrameGame.getInstance().getMousePos();
		setLocation(pt.x - getWidth()/2, pt.y - getHeight()/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHp = new JLabel("HP: " + buff.getHPBuff() + " (" + Utility.format(buff.getHPBuff()/prof*0.01) + "/prof)");
		lblHp.setBounds(10, 11, 190, 14);
		contentPane.add(lblHp);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(210, 0, 16, 259);
		contentPane.add(separator);
		
		JLabel lblMp = new JLabel("MP: " + buff.getMPBuff() + " (" + Utility.format(buff.getMPBuff()/prof*0.01) + "/prof)");;
		lblMp.setBounds(10, 36, 190, 14);
		contentPane.add(lblMp);
		
		JLabel lblSp = new JLabel("SP: " + buff.getSPBuff() + " (" + Utility.format(buff.getSPBuff()/prof*0.01) + "/prof)");
		lblSp.setBounds(10, 61, 190, 14);
		contentPane.add(lblSp);
		
		JLabel lblBal = new JLabel("Bal: " + buff.getBalBuff() + " (" + Utility.format(buff.getBalBuff()/prof*0.01) + "/prof)");
		lblBal.setBounds(10, 86, 190, 14);
		contentPane.add(lblBal);
		
		JLabel lblStr = new JLabel("Str: " + buff.getStrBuff() + " (" + Utility.format(buff.getStrBuff()/prof*0.01) + "/prof)");
		lblStr.setBounds(10, 111, 190, 14);
		contentPane.add(lblStr);
		
		JLabel lblInt = new JLabel("Int: " + buff.getIntBuff() + " (" + Utility.format(buff.getIntBuff()/prof*0.01) + "/prof)");
		lblInt.setBounds(10, 136, 190, 14);
		contentPane.add(lblInt);
		
		JLabel lblDex = new JLabel("Dex: " + buff.getDexBuff() + " (" + Utility.format(buff.getDexBuff()/prof*0.01) + "/prof)");
		lblDex.setBounds(10, 161, 190, 14);
		contentPane.add(lblDex);
		
		JLabel lblAgi = new JLabel("Agi: " + buff.getAgiBuff() + " (" + Utility.format(buff.getAgiBuff()/prof*0.01) + "/prof)");
		lblAgi.setBounds(10, 186, 190, 14);
		contentPane.add(lblAgi);
		
		JLabel lblDef = new JLabel("Def: " + buff.getDefBuff() + " (" + Utility.format(buff.getDefBuff()/prof*0.01) + "/prof)");
		lblDef.setBounds(10, 211, 190, 14);
		contentPane.add(lblDef);
		
		JLabel lblProt = new JLabel("Prot: " + buff.getProtBuff() + " (" + Utility.format(buff.getProtBuff()/prof*0.01) + "/prof)");
		lblProt.setBounds(10, 236, 190, 14);
		contentPane.add(lblProt);
		
		JLabel lblHpRegen = new JLabel("HP Regen: " + buff.getHPRegenBuff() + " (" + Utility.format(buff.getHPRegenBuff()/prof*0.01) + "/prof)");
		lblHpRegen.setBounds(223, 11, 190, 14);
		contentPane.add(lblHpRegen);
		
		JLabel lblMpRegen = new JLabel("MP Regen: " + buff.getMPRegenBuff() + " (" + Utility.format(buff.getMPRegenBuff()/prof*0.01) + "/prof)");
		lblMpRegen.setBounds(223, 36, 190, 14);
		contentPane.add(lblMpRegen);
		
		JLabel lblSpRegen = new JLabel("SP Regen: " + buff.getSPRegenBuff() + " (" + Utility.format(buff.getSPRegenBuff()/prof*0.01) + "/prof)");
		lblSpRegen.setBounds(223, 61, 190, 14);
		contentPane.add(lblSpRegen);
		
		JLabel lblBalRegen = new JLabel("Bal Regen: " + buff.getBalRegenBuff() + " (" + Utility.format(buff.getBalRegenBuff()/prof*0.01) + "/prof)");
		lblBalRegen.setBounds(223, 86, 190, 14);
		contentPane.add(lblBalRegen);
		setVisible(true);
	}
	@Override
	public void toFront() {
		
	}
}
