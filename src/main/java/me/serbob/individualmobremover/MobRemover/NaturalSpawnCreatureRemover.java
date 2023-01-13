package me.serbob.individualmobremover.MobRemover;

import me.serbob.individualmobremover.IndividualMobRemover;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class NaturalSpawnCreatureRemover implements Listener {
    private final IndividualMobRemover plugin;

    public NaturalSpawnCreatureRemover(IndividualMobRemover plugin) {
        this.plugin=plugin;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        String entityName = event.getEntityType().name();

        if (plugin.getConfig().getStringList("restricted-mobs").contains(entityName)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntitySpawn(EntitySpawnEvent event) {
        String entityName = event.getEntityType().name();

        if (plugin.getConfig().getStringList("restricted-mobs").contains(entityName)) {
            event.setCancelled(true);
        }
    }
}
