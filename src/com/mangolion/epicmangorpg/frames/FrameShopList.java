package com.mangolion.epicmangorpg.frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class FrameShopList extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameShopList frame = new FrameShopList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameShopList() {
		setBounds(100, 100, 693, 448);
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblSales = new JLabel("Sales");
		lblSales.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblSales);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 392, 39);
		panel.add(lblName);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 61, 392, 95);
		panel.add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		
		JLabel lblDesc = new JLabel("Desc");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblDesc);
		
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setBounds(10, 167, 91, 23);
		panel.add(btnViewDetails);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(111, 167, 91, 23);
		panel.add(btnBuy);
		
		JButton button = new JButton("New button");
		button.setBounds(212, 167, 91, 23);
		panel.add(button);

	}
}
