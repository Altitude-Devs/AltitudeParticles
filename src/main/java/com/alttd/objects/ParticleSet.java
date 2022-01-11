package com.alttd.objects;

import com.alttd.AltitudeParticles;
import com.alttd.frameSpawners.FrameSpawnerLocation;
import com.alttd.frameSpawners.FrameSpawnerPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class ParticleSet {

    List<Frame> frames;
    int delay, repeat;
    APartType particlesType;
    boolean shouldRepeat;
    int ticksUntilNextFrame;

    public ParticleSet(List<Frame> frames, int delay, int repeat, APartType particlesType) {
        this.frames = frames;
        this.delay = delay;
        this.repeat = repeat;
        this.particlesType = particlesType;
        this.shouldRepeat = repeat < 0;
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
}
