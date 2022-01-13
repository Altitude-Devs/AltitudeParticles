package com.alttd.objects;

import com.alttd.AltitudeParticles;
import com.alttd.config.Config;
import com.alttd.frameSpawners.FrameSpawnerLocation;
import com.alttd.frameSpawners.FrameSpawnerPlayer;
import com.alttd.storage.PlayerSettings;
import com.alttd.util.Logger;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ParticleSet {

    private final List<Frame> frames;
    private final int frameDelay, repeat, repeatDelay;
    private final APartType aPartType;
    private final String uniqueId;
    private final String permission;
    private final ItemStack itemStack;

    public ParticleSet(List<Frame> frames, int frameDelay, int repeat, int repeatDelay, APartType aPartType, String uniqueId, String permission, ItemStack itemStack) {
        this.frames = frames;
        this.frameDelay = frameDelay;
        this.repeat = repeat;
        this.repeatDelay = repeatDelay;
        this.aPartType = aPartType;
        this.uniqueId = uniqueId;
        this.permission = permission;
        this.itemStack = itemStack;
    }

    public void run(Location location) {
        FrameSpawnerLocation frameSpawnerLocation = new FrameSpawnerLocation(repeat, repeatDelay, frames, location);
        frameSpawnerLocation.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, frameDelay);
    }

    public void run(Player player, PlayerSettings playerSettings) {
        if (Config.DEBUG)
            Logger.info("Starting particle set % for %.", uniqueId, player.getName());
        FrameSpawnerPlayer frameSpawnerPlayer = new FrameSpawnerPlayer(repeat, repeatDelay, frames, player, playerSettings, aPartType, uniqueId);
        frameSpawnerPlayer.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, frameDelay);
    }

    public APartType getAPartType() {
        return aPartType;
    }

    public String getPermission() {
        return permission;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getParticleId() {
        return uniqueId;
    }
}
