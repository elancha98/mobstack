package me.commandcraft.mobstack;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobSpawn implements Listener{
	
	public boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getNumber(String s) {
		return Integer.parseInt(s);
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event){
		if (event.getSpawnReason().equals(SpawnReason.SPAWNER)) {
			Entity spawned = event.getEntity();
			List<Entity> nearbyE = spawned.getNearbyEntities(20, 20, 20);
			for (Entity e : nearbyE) {
				if (e.getType().equals(event.getEntityType()) && isNumber(e.getCustomName())) {
					int n = getNumber(e.getCustomName());
					e.setCustomName(Integer.toString(n + 1));
					e.setCustomNameVisible(true);
					event.setCancelled(true);
					return;
				}
			}
			spawned.setCustomName("1");
		}
	}

	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity spawned = event.getEntity();
		if (isNumber(spawned.getCustomName())) {
			int n = getNumber(spawned.getCustomName());
			if (n > 1){
				EntityType type = event.getEntityType();
				 Entity nueva = spawned.getLocation().getWorld().spawnEntity(spawned.getLocation(), type);
				 nueva.setCustomName(Integer.toString(n-1));
			}
		}
	}
}
