package com.mangolion.epicmangorpg.frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JButton;

import com.mangolion.epicmangorpg.items.ItemCustom;

public class FrameViewItemCustom extends JInternalFrame {

	ItemCustom item;
	/**
	 * Create the frame.
	 */
	public FrameViewItemCustom(final ItemCustom item) {
		super(item.name);
		this.item = item;
		setBounds(100, 100, 343, 259);
		getContentPane().setLayout(null);
		FrameGame.getInstance().addFrame(this);
		JLabel lblName = new JLabel(item.name);
		lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblName.setBounds(10, 10, 312, 46);
		getContentPane().add(lblName);
		
		JTextArea tfDesc = new JTextArea(item.getDesc());
		tfDesc.setLineWrap(true);
		tfDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		tfDesc.setEditable(false);
		tfDesc.setBounds(10, 67, 312, 70);
		getContentPane().add(tfDesc);
		
		JLabel lblShopPrice = new JLabel("Shop Price:");
		lblShopPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblShopPrice.setBounds(10, 148, 310, 14);
		getContentPane().add(lblShopPrice);
		
		JButton btnUse = new JButton("Use");
		btnUse.setEnabled(item.consumable);
		btnUse.setBounds(112, 187, 91, 23);
		btnUse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				item.onUse();
				dispose();
			}
		});
		getContentPane().add(btnUse);

	}
}
