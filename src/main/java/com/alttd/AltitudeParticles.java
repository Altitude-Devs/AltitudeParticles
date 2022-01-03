package com.alttd;

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

    }

}
