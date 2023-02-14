package dev.sefiraat.cultivation.api.events;

import dev.sefiraat.cultivation.api.slimefun.items.bushes.CultivationBush;
import dev.sefiraat.cultivation.api.slimefun.items.plants.CultivationPlant;
import org.bukkit.Location;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This event is fired before a bush grows a stage. This event can be cancelled to stop growth
 *
 * @see CultivationPlant
 */
public class CultivationBushGrowEvent extends CultivationGrowEvent<CultivationBush> {

    @ParametersAreNonnullByDefault
    public CultivationBushGrowEvent(Location location, CultivationBush growingBush, int growthStage) {
        super(location, growingBush, growthStage);
    }
}
