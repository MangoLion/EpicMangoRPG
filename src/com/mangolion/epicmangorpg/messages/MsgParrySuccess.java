package com.mangolion.epicmangorpg.messages;

public class MsgParrySuccess extends Msg {
	public MsgParrySuccess() {
		super(
				"$name's $weapon had been brought up and deflected $targetname's $targetweapon successfully",
				"$name  had parried $targetname's $targetskill with $p3 $weapon");
	}
}
