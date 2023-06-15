package com.hypsEater;

import com.example.EthanApiPlugin.EthanApiPlugin;
import com.example.PacketUtils.PacketUtilsPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HypsEaterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HypsEaterPlugin.class, EthanApiPlugin.class, PacketUtilsPlugin.class);
		RuneLite.main(args);
	}
}