package com.mangolion.epicmangorpg.messages;

public class MsgSkillInterruptFail extends Msg {
	public MsgSkillInterruptFail() {
		super(
				"$name had attempted to interrupt $targetname's $targetskill, but because of $targetname's balance, $p failed.",
				"$targetname's balanced stance did not allow $name to interupt $targetp3 $targetskill");
	}
}
