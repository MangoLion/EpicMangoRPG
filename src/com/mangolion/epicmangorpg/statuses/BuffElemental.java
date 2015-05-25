package com.mangolion.epicmangorpg.statuses;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;

public class BuffElemental extends Buff {

	public BuffElemental(String name,float time, Element element) {
		super(name, 0, time, GenType.positive, Type.elemental);
		this.element = element;
	}

}
