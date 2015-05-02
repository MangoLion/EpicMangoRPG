package com.mangolion.epicmangorpg.messages;

public class MsgSlashMiss extends Msg {
	public MsgSlashMiss() {
		super(
				"$name 's $weapon slice the air in a wide arc, missing its target.",
				"$name 's $weapon swung swiftly at its target, however, $targetname had leaned outwards and dodged it by a hair's bredth.");
	}
}
