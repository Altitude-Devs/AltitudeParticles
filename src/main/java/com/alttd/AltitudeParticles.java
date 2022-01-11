package com.alttd;

import com.alttd.commands.CommandManager;
import com.alttd.config.Config;
import com.alttd.config.DatabaseConfig;
import com.alttd.database.Database;
import com.alttd.util.Logger;
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
        Logger.info("--------------------------------------------------");
        Logger.info("Altitude Particles started");
        Logger.info("--------------------------------------------------");
    }

}
