package com.hypsEater;

import com.example.InteractionApi.InventoryInteraction;
import com.example.InventoryUtils.InventoryUtils;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

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

	@Subscribe
	public void onGameTick(GameTick gameTick) {
		int hp = client.getBoostedSkillLevel(Skill.HITPOINTS);

		if (InventoryUtils.inventoryContainsEdibleFoods())
		{
			int foodToEat = InventoryUtils.findItemWithEatOption();
			System.out.println(foodToEat);

			InventoryInteraction.useItem(foodToEat,"Eat");
		}else{
			System.out.println("No Edibles");
		}
	}

	@Provides
	HypsEaterConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(HypsEaterConfig.class);
	}

}
