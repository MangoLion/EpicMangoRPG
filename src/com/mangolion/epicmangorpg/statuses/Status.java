package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;

public class Status implements Cloneable{
	
		public Character character;
		public String name;
		public float time = 0;
		public float multiplier = 1;
		public boolean doStun = false;
		
		public Status(Character character_, String name_, float time_) {
			character =  character_;
			name = name_;
			time = time_;
		}
		
		public void tick (float deltaTime){
			time = Utility.format(time - deltaTime);
		}
		
		public void remove() {
			StylePainter.append(new Msg("$"
					+ "name is no longer " + name).getMessage(character, null, 0));
		}

		public void onUpdate(){
			
		}
		
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return name + "(" +time + ")";
		}
		
		public Status copy(){
			try {
				return (Status) clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
