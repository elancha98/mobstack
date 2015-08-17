package me.commandcraft.mobstack;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public final static Logger logger = Logger.getLogger("Minecraft");

	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new MobSpawn(), this);
	}

	public void onDisable() {

	}
}
