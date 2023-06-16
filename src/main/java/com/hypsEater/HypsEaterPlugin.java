package com.hypsEater;

import com.example.InteractionApi.InventoryInteraction;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.StatChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import com.example.InventoryUtils.InventoryChecker;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@PluginDescriptor(
		name = "<html><font color=#c513ff>Hyps</font> Eater</html>"
)
public class HypsEaterPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private HypsEaterConfig config;

	@Inject
	private ItemManager itemManager;


	String[] menuActions = {"Eat"};

	@Subscribe
	public void onGameTick(GameTick gameTick) {
		int hp = client.getBoostedSkillLevel(Skill.HITPOINTS);

		if (InventoryChecker.inventoryContainsEdibleFoods())
		{
			int foodToEat = InventoryChecker.findItemWithEatOption();
			System.out.println(foodToEat);

			InventoryInteraction.useItem(foodToEat,"Eat");
		}else{
			System.out.println("No Ediles");
		}
	}

	private boolean hasEatOption(String menuAction) {
			return menuAction != null && menuAction.contains("Eat");
		}
	@Subscribe
	public void onStatChanged(StatChanged statChanged) {
	}

	@Provides
	HypsEaterConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(HypsEaterConfig.class);
	}

}
