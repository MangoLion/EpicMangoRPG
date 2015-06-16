package com.mangolion.epicmangorpg.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.mangolion.epicmangorpg.components.LogMsg;

import javax.swing.SwingConstants;

public class FrameLogInfo extends JInternalFrame {

	private JPanel contentPane;
	public LogMsg msg;
	
	JTextPane lblLog;
	/**
	 * Create the frame.
	 */
	public FrameLogInfo(LogMsg msg) {
		super("", true, true, true, true);
		FrameGame.getInstance().addFrame(this);
		this.msg = msg;
		setBounds(100, 100, 399, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLog = new JTextPane();
		lblLog.setBounds(10, 11, 363, 118);
		contentPane.add(lblLog);
		
		setVisible(true);
		refresh();
	}
	
	public void refresh(){
		lblLog.setText(msg.text);
		setTitle("" + msg.time + " seconds");
	}

}
