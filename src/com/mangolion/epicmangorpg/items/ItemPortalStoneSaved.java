package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Game;

public class ItemPortalStoneSaved extends ItemCustom{

	public ItemPortalStoneSaved() {
		super("Saved Portal Stone", "This item has saved the location when you first used it, use it again to teleport back", Type.Craft, 2);
		consumable = true;
	}
	
	@Override
	public void onUse() {
		Game.getInstance().currentFloor = Integer.valueOf((String) values.get(0));
		Game.getInstance().floorPercent = Float.valueOf((String) values.get(1));
		Game.getInstance().begin();
		
		CharacterPlayer.instance.inventory.itemCustoms.remove(this);
		super.onUse();
	}
	
	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return super.getDesc() + " This stone has saved floor "  + values.get(0) + ", " + values.get(1) + " percent";
	}

}
