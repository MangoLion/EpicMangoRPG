package com.mangolion.epicmangorpg.game;

import java.text.DecimalFormat;

import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.messages.Msg;

public class Utility {
	public static DecimalFormat decimalFormat = new DecimalFormat("0.00");

	public static float format(double d) {
		return Math.round(d*100f)/100f;
	}
	
	public static float format4(double d) {
		return Math.round(d*10000f)/10000f;
	}

	public static void narrate(String text) {
		//FrameGame.instance.appendNarration(text);
		StylePainter.append(new Msg(text).getMessage(null, null, 0));
	}
	
	 public static <T> T getInstance(Class<T> theClass) {

		    try {
				return theClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
