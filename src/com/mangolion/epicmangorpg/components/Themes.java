package com.mangolion.epicmangorpg.components;


import java.util.ArrayList;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.AutumnSkin;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;
import org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin;
import org.pushingpixels.substance.api.skin.BusinessSkin;
import org.pushingpixels.substance.api.skin.ChallengerDeepSkin;
import org.pushingpixels.substance.api.skin.CremeCoffeeSkin;
import org.pushingpixels.substance.api.skin.CremeSkin;
import org.pushingpixels.substance.api.skin.DustCoffeeSkin;
import org.pushingpixels.substance.api.skin.DustSkin;
import org.pushingpixels.substance.api.skin.EmeraldDuskSkin;
import org.pushingpixels.substance.api.skin.GeminiSkin;
import org.pushingpixels.substance.api.skin.GraphiteAquaSkin;
import org.pushingpixels.substance.api.skin.GraphiteSkin;
import org.pushingpixels.substance.api.skin.MagellanSkin;
import org.pushingpixels.substance.api.skin.MarinerSkin;
import org.pushingpixels.substance.api.skin.MistAquaSkin;
import org.pushingpixels.substance.api.skin.MistSilverSkin;
import org.pushingpixels.substance.api.skin.ModerateSkin;
import org.pushingpixels.substance.api.skin.NebulaBrickWallSkin;
import org.pushingpixels.substance.api.skin.NebulaSkin;
import org.pushingpixels.substance.api.skin.OfficeBlack2007Skin;
import org.pushingpixels.substance.api.skin.OfficeBlue2007Skin;
import org.pushingpixels.substance.api.skin.OfficeSilver2007Skin;
import org.pushingpixels.substance.api.skin.RavenSkin;
import org.pushingpixels.substance.api.skin.SaharaSkin;
import org.pushingpixels.substance.api.skin.TwilightSkin;

import com.mangolion.epicmangorpg.game.StylePainter;


public class Themes {
	public static String current;
	public static SubstanceSkin lightthemes[] = { new BusinessSkin(),
			new BusinessBlueSteelSkin(), new BusinessBlackSteelSkin(),
			new CremeSkin(), new CremeCoffeeSkin(), new SaharaSkin(),
			new ModerateSkin(), new NebulaSkin(), new NebulaBrickWallSkin(),
			new AutumnSkin(), new MistSilverSkin(), new MistAquaSkin(),
			new DustSkin(), new DustCoffeeSkin(), new GeminiSkin(),
			new MarinerSkin(), new OfficeBlack2007Skin(), new OfficeBlue2007Skin(),
			new OfficeSilver2007Skin()};
	
	public static SubstanceSkin darkthemes[] = {new TwilightSkin(), new MagellanSkin(),
		new GraphiteSkin(), new GraphiteAquaSkin(), new RavenSkin(), new ChallengerDeepSkin(),
		new EmeraldDuskSkin()};

	public static ArrayList<String> getLightThemeList() {
		ArrayList<String> str = new ArrayList<String>();
		for (SubstanceSkin skin : lightthemes)
			str.add(skin.getDisplayName());
		return str;
	}
	
	public static ArrayList<String> getDarkThemeList() {
		ArrayList<String> str = new ArrayList<String>();
		for (SubstanceSkin skin : darkthemes)
			str.add(skin.getDisplayName());

		return str;
	}

	public static void setTheme(String name){
		for (SubstanceSkin skin:lightthemes)
			if (skin.getDisplayName().equals(name)){
				SubstanceLookAndFeel.setSkin(skin);
				StylePainter.updateColors(true);
			}
		for (SubstanceSkin skin:darkthemes)
			if (skin.getDisplayName().equals(name)){
				SubstanceLookAndFeel.setSkin(skin);
				StylePainter.updateColors(false);
			}
		current = name;
	}

	public static String getCurrentTheme() {
		// TODO Auto-generated method stub
		return current;
	}
}
