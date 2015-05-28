package com.mangolion.epicmangorpg.messages;

public class MsgBasicCD extends Msg {
	public MsgBasicCD() {
		super(
				"As $name finished $p3 $skill, $p had left an openning for .",
				"$name  had left an openning for $num seconds when $p finished $p3 $skill .",
				"$name is switching back from $p3 $skill stance and is vulnerable for ",
				"$name is transitioning from $p3 $skill stance and is vulnerable for ");
	}
}
