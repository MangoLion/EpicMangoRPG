package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FrameGameInfo extends JFrame {
	
	JPanel contentPane = new JPanel(new BorderLayout());
	JTextPane tfContent = new JTextPane();
	JScrollPane scrollContent;
	public FrameGameInfo() {
		setSize(800,600);
		setVisible(true);
		setContentPane(contentPane);
		scrollContent = new JScrollPane(tfContent);
		contentPane.add(scrollContent);
		
		try {
			tfContent.setText(FileUtils.readFileToString(FrameGame.infoFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JScrollBar scroll = scrollContent.getVerticalScrollBar();
				scroll = FrameGame.instance.scrollLog.getVerticalScrollBar();
				scroll.setValue(scroll.getMinimum());
				System.out.println("scrolled");
			}
		});
	}
}
