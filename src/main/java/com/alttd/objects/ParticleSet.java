package com.alttd.objects;

import com.alttd.AltitudeParticles;
import com.alttd.frameSpawners.FrameSpawnerLocation;
import com.alttd.frameSpawners.FrameSpawnerPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ParticleSet {

    private final List<Frame> frames;
    private final int delay, repeat;
    private final APartType aPartType;
    private final String uniqueId;
    private final String permission;
    private final ItemStack itemStack;

    public ParticleSet(List<Frame> frames, int delay, int repeat, APartType aPartType, String uniqueId, String permission, ItemStack itemStack) {
        this.frames = frames;
        this.delay = delay;
        this.repeat = repeat;
        this.aPartType = aPartType;
        this.uniqueId = uniqueId;
        this.permission = permission;
        this.itemStack = itemStack;
    }

    public void run(Location location) {
        FrameSpawnerLocation frameSpawnerLocation = new FrameSpawnerLocation(repeat, frames, location);
        frameSpawnerLocation.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, delay);
    }

    public void run(Player player) {
        FrameSpawnerPlayer frameSpawnerPlayer = new FrameSpawnerPlayer(repeat, frames, player);
        frameSpawnerPlayer.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, delay);
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
