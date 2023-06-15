package com.hypsEater;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface HypsEaterConfig extends Config
{
	@ConfigItem(
			keyName = "eatAtHP",
			name = "HP to eat at",
			description = "This will eat at the hp equal to the configured setting"
	)
	default int eatAtHP()
	{
		return 20;
	}
}
