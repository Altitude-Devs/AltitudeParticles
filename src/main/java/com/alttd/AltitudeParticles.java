package com.alttd;

import com.alttd.commands.CommandManager;
import com.alttd.config.Config;
import com.alttd.config.DatabaseConfig;
import com.alttd.database.Database;
import com.alttd.listeners.*;
import com.alttd.objects.APartType;
import com.alttd.particles.InitParticles;
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
        Database.getDatabase().init();
        new CommandManager();
        registerEvents();
        InitParticles.init();
        Logger.info("--------------------------------------------------");
        Logger.info("Altitude Particles started");
        Logger.info("--------------------------------------------------");
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(APartType.TRAIL, APartType.HEAD), this);
        pluginManager.registerEvents(new BlockBreakListener(APartType.BREAK_PLACE_BLOCK), this);
        pluginManager.registerEvents(new BlockPlaceListener(APartType.BREAK_PLACE_BLOCK), this);
        pluginManager.registerEvents(new DeathListener(APartType.DEATH), this);
        pluginManager.registerEvents(new GUIListener(), this);
    }

}
