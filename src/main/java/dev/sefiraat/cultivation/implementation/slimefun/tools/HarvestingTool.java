package dev.sefiraat.cultivation.implementation.slimefun.tools;

import dev.sefiraat.cultivation.api.slimefun.items.plants.HarvestablePlant;
import dev.sefiraat.sefilib.slimefun.items.RefillableUseItem;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class HarvestingTool extends RefillableUseItem {

    /**
     * Creates a new {@link RefillableUseItem}.
     *
     * @param group      The {@link ItemGroup} this item belongs to.
     * @param item       The {@link SlimefunItemStack} that is used to create this item.
     * @param recipeType The {@link RecipeType} of this item.
     * @param recipe     The recipe of this item.
     */
    public HarvestingTool(ItemGroup group,
                          SlimefunItemStack item,
                          RecipeType recipeType,
                          ItemStack[] recipe,
                          int maxUses
    ) {
        super(group, item, recipeType, recipe);
        this.setMaxUseCount(maxUses);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return playerRightClickEvent -> {
            if (playerRightClickEvent.getClickedBlock().isEmpty()) {
                // No block preset
                return;
            }

            final Block block = playerRightClickEvent.getClickedBlock().get();
            final SlimefunItem item = BlockStorage.check(block);

            if (item instanceof HarvestablePlant harvestable
                && harvestable.isHarvestable()
                && harvestable.isHarvestReady(block)
            ) {
                final ItemStack harvestResult = harvestable.getHarvestingResult();
                if (harvestResult == null) {
                    // shouldn't be possible, but just to be safe
                    return;
                }
                harvestable.updateGrowthStage(block, 1);
                block.getWorld().dropItem(block.getLocation(), harvestResult.clone());
            }

            damageItem(playerRightClickEvent.getPlayer(), playerRightClickEvent.getItem());
        };
    }
}
