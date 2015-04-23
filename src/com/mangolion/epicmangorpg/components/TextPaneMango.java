package com.mangolion.epicmangorpg.components;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

public class TextPaneMango extends JTextPane {
	public TextPaneMango() {
		
	}
	
	public void append(String text){
		Document document = getDocument();
		try {
			document.insertString(document.getLength(), text, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
