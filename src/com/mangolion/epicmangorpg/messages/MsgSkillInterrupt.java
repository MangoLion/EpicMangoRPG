package com.mangolion.epicmangorpg.messages;

public class MsgSkillInterrupt extends Msg {
	public MsgSkillInterrupt() {
		super(
				"$name had swiftly moved close to $targetname and interupted $tp3 $targetskill.",
				"$targetname had acted too late and was interupted by $name 's $skill.");
	}
}
