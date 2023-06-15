package com.hypsEater;

import com.example.InteractionApi.InventoryInteraction;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import com.example.InventoryUtils.InventoryChecker;

import java.util.Set;

@Slf4j
@PluginDescriptor(
		name = "Example"
)
public class HypsEaterPlugin extends Plugin
{
	private final Set<Integer> EDIBLE_FOODS = Set.of(ItemID.ADMIRAL_PIE,ItemID.HALF_AN_ADMIRAL_PIE,ItemID.ANCHOVIES,ItemID.ANCHOVY_PIZZA,ItemID._12_ANCHOVY_PIZZA,ItemID.ANGLERFISH);

	@Inject
	private Client client;

	@Inject
	private HypsEaterConfig config;


	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		int hp = client.getBoostedSkillLevel(Skill.HITPOINTS);

		if (hp<config.eatAtHP() && InventoryChecker.contains(client, EDIBLE_FOODS))
		{
			InventoryInteraction.useItem(EDIBLE_FOODS, "Eat");
			System.out.println("Eating");
		}
	}
	@Subscribe
	public void onStatChanged(StatChanged statChanged)
	{
	}

	@Provides
	HypsEaterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HypsEaterConfig.class);
	}
}
