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
    private final APartType particlesType;
    private final boolean shouldRepeat;
    private final String permission;
    private final ItemStack itemStack;
    private int ticksUntilNextFrame;

    public ParticleSet(List<Frame> frames, int delay, int repeat, APartType particlesType, String permission, ItemStack itemStack) {
        this.frames = frames;
        this.delay = delay;
        this.repeat = repeat;
        this.particlesType = particlesType;
        this.shouldRepeat = repeat < 0;
        this.permission = permission;
        this.itemStack = itemStack;
        ticksUntilNextFrame = delay;
    }

    public void run(Location location) {
        FrameSpawnerLocation frameSpawnerLocation = new FrameSpawnerLocation(repeat, frames, location);
        frameSpawnerLocation.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, delay);
    }

    public void run(Player player) {
        FrameSpawnerPlayer frameSpawnerPlayer = new FrameSpawnerPlayer(repeat, frames, player);
        frameSpawnerPlayer.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, delay);
    }

    public String getPermission() {
        return permission;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
