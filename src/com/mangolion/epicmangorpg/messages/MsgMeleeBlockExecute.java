package com.mangolion.epicmangorpg.messages;

public class MsgMeleeBlockExecute extends Msg {
	public MsgMeleeBlockExecute() {
		super(
				"$name's $weapon is now raised and in position, ready to receive an attack.",
				"$name  has readied $p3 stance, prepared for the incomming strike.");
	}
}
