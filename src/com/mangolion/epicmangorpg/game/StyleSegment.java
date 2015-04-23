package com.mangolion.epicmangorpg.game;

import javax.swing.text.Style;

public class StyleSegment {
	public Style style;
	public String text;
	public boolean nextLine = false;
	
	public StyleSegment(Style style_, String str) {
		style = style_;
		text = str;
	}
	
	public StyleSegment(Style style_, String str, boolean nextLine_) {
		style = style_;
		text = str;
		nextLine = nextLine_;
	}
			
}
