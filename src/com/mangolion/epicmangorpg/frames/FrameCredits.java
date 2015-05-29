package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class FrameCredits extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCredits frame = new FrameCredits();
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
	public FrameCredits() {
		setTitle("Credits");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 294, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		DefaultListModel<Credit> model = new DefaultListModel<Credit>();
		final JList<Credit> listCredits = new JList<Credit>(model);
		scrollPane.setViewportView(listCredits);
		listCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					Credit credit = listCredits.getSelectedValue();
					
					 Desktop d=Desktop.getDesktop();

					 // Browse a URL, say google.com
					 try {
						d.browse(new URI(credit.link));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
				
				super.mouseClicked(e);
			}
		});
		model.addElement(new Credit("Substance for GUI framework", "https://java.net/projects/substance/"));
		model.addElement(new Credit("Inspired by iyouboushi's battle bot", "https://github.com/Iyouboushi/mIRC-BattleArena"));
		model.addElement(new Credit("Damage system Inspired by Kupo707's Epic Battle Fantasy", "http://kupogames.com/web-games/epic-battle-fantasy-4/"));
	}

	public static class Credit{
		public String name, link;
		public Credit(String name_, String link_) {
			name = name_;
			link= link_;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return name;
		}
	}
	

}
