package com.mangolion.epicmangorpg.messages;

public class MsgMeleeBlockLoad extends Msg {
	public MsgMeleeBlockLoad() {
		super(
				"Seeing an imminent attack, $name attempts to raise $p3 $weapon to defend.",
				"Concluding that dodging is impossible for the next attack, $name's weapon is raised to block it. ");
	}
}
