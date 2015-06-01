package com.mangolion.epicmangorpg.messages;

public class MsgMissLuck extends Msg{
public MsgMissLuck() {
	super("$targetname 's $str has passed by $name $targetskill by a hairsbredth!",
			"Seeing $targetname's $str heading towards $name, $p could only watch helplessly as $tp $str ... missed harmlessly.",
			"$targetname's $str heads towards $name with inredible accuracy, however, $name had  leaned $p3 body and managed to dodge it stylishly! (or is $p just lucky?)");
}
}
