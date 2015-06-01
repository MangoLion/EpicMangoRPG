package com.mangolion.epicmangorpg.game;

import java.awt.Color;
import java.security.acl.LastOwnerException;
import java.util.LinkedList;

import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;

import com.mangolion.epicmangorpg.components.TextPaneMango;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.messages.Msg;

public class StylePainter {
	static TextPaneMango paneMango,
			paneTime;
	public static Style NAME, STATUS, NUMBER, SKILL, NORMAL, BOLD, NULL;
	public static Color colName,	colStatus , colSkill,colNumber, colNormal;
	public static boolean lightTheme = true;
	public static Style styles[] = {NAME, SKILL, NUMBER, NORMAL, STATUS};
	static int lineNum = 0;
	
	public StylePainter() {
		//updateColors(true);
	}
	
	public static void init(){
		paneMango = FrameGame.instance.tfNarration;
		NAME = paneMango.addStyle("Name", null);
				STATUS = paneMango.addStyle("Status", null);
				SKILL = paneMango.addStyle("Skill", null);
				NUMBER = paneMango.addStyle("Number", null);
				NORMAL = paneMango.addStyle("Normal", null);
				BOLD = paneMango.addStyle("Bold", null);
				NULL = null; 
	}
	
	public static void updateColors(boolean light){
		lightTheme = light;
		if (light){
					colName  = Color.decode("#A34900");
					colStatus = Color.decode("#007173");
					colSkill = Color.decode("#004763");
					colNumber =Color.decode("#007173");
					colNormal =  Color.black;
		}else {
				colName  = Color.orange;
				colStatus = Color.cyan;
				colSkill = Color.yellow;
				colNumber = Color.yellow;
				colNormal = Color.white;
		}
		if (NAME == null)
			return;
		
		StyleConstants.setForeground(NAME, colName);
		StyleConstants.setForeground(STATUS, colStatus);
		StyleConstants.setForeground(SKILL, colSkill);
		StyleConstants.setForeground(NUMBER, colNumber);
		StyleConstants.setForeground(NORMAL, colNormal);
		StyleConstants.setBold(BOLD, true);

	}
	
	public static void append(StyleSegment ... segments){
		if (FrameGame.getInstance().viewMode)
			return;
		
		 paneMango = FrameGame.instance.tfNarration;
			paneTime = FrameGame.instance.tfTime;
		StyledDocument document = paneMango.getStyledDocument();
		lineNum ++;
		for (StyleSegment segment: segments){
			try {
				document.insertString(document.getLength(), segment.text, segment.style);
				if (segment.nextLine)
					document.insertString(document.getLength(), "\n", segment.style);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JScrollBar scroll = FrameGame.instance.scrollMango.getVerticalScrollBar();
				scroll.setValue(scroll.getMaximum());
				scroll = FrameGame.instance.scrollLog.getVerticalScrollBar();
				scroll.setValue(scroll.getMaximum());
			}
		});
		if (lineNum > 1){
			paneTime.append("\n");
			//System.out.println("appended");
		}
	}

	@SafeVarargs
	public static void append(LinkedList<StyleSegment> ... message){
		if (FrameGame.getInstance().viewMode)
			return;
		LinkedList<StyleSegment> result = new LinkedList<StyleSegment>();
		for (int i = 0; i < message.length; i ++){
			LinkedList<StyleSegment> s =  message[i];
			result.addAll(s);
		}
		append(result);
	}
	
	public static void append(LinkedList<StyleSegment> message) {
		if (FrameGame.getInstance().viewMode)
			return;
		 paneMango = FrameGame.instance.tfNarration;
			paneTime = FrameGame.instance.tfTime;
		lineNum ++;
		StyledDocument document = paneMango.getStyledDocument();
		for (StyleSegment segment: message){
			try {
				document.insertString(document.getLength(), segment.text, segment.style);
				if (segment.nextLine)
					document.insertString(document.getLength(), "\n", segment.style);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JScrollBar scroll = FrameGame.instance.scrollMango.getVerticalScrollBar();
				scroll.setValue(scroll.getMaximum());
				scroll = FrameGame.instance.scrollLog.getVerticalScrollBar();
				scroll.setValue(scroll.getMaximum());
			}
		});
		//System.out.println("" + lineNum);
		if (lineNum > 1 || paneTime.getText().equals("")){
			paneTime.append("\n");
		//	System.out.println("appended");
		}
	}
	
	public static void appendTime(float time) {
		if (FrameGame.getInstance().viewMode)
			return;
		paneTime = FrameGame.instance.tfTime;
		 paneMango = FrameGame.instance.tfNarration;
		//System.out.println("-" + lineNum);
		String str = "[ $num ]";
		lineNum = 0;
		LinkedList<StyleSegment> message = new Msg( str).getMessage(null, null, time);
		StyledDocument document = paneTime.getStyledDocument();
		for (StyleSegment segment: message){
			try {
				document.insertString(document.getLength(), segment.text, segment.style);
				if (segment.nextLine)
					document.insertString(document.getLength(), "\n", segment.style);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JScrollBar scroll = FrameGame.instance.scrollMango.getVerticalScrollBar();
				scroll.setValue(scroll.getMaximum());
			}
		});
		//paneMango.append("<>");
		//append(new Msg(false, "<>").getMessage(null, null, 0));
	}
} 
