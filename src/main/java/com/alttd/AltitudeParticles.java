package com.alttd;

import com.alttd.commands.CommandManager;
import com.alttd.config.Config;
import com.alttd.config.DatabaseConfig;
import com.alttd.listeners.GUIListener;
import com.alttd.listeners.BlockBreakListener;
import com.alttd.listeners.BlockPlaceListener;
import com.alttd.listeners.PlayerJoinListener;
import com.alttd.util.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AltitudeParticles extends JavaPlugin {

    public static AltitudeParticles instance;

    public static AltitudeParticles getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Config.reload();
        DatabaseConfig.reload();
//        Database.getDatabase().init();
        new CommandManager();
        registerEvents();
        Logger.info("--------------------------------------------------");
        Logger.info("Altitude Particles started");
        Logger.info("--------------------------------------------------");
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new GUIListener(), this);
    }

}
