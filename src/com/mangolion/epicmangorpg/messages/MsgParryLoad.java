package com.mangolion.epicmangorpg.messages;

public class MsgParryLoad extends Msg {
	public MsgParryLoad() {
		super(
				"Seeing the next attack as a threat, $name swing $p3 $weapon forward, hoping to parry it",
				"$name is attempting to parry the incomming attack.");
	}
}
