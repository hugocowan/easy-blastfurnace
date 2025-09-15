package com.toofifty.easyblastfurnace.steps;

import lombok.Getter;

public class NpcStep extends MethodStep
{
	@Getter
	private final int npcID;

	public NpcStep(String tooltip, int npcID)
	{
		super(tooltip);
		this.npcID = npcID;
	}
}
