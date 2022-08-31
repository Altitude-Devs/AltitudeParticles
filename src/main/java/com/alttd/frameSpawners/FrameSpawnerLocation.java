package com.alttd.frameSpawners;

import com.alttd.AltitudeParticles;
import com.alttd.config.Config;
import com.alttd.objects.Frame;
import com.alttd.util.Logger;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public class FrameSpawnerLocation extends BukkitRunnable {

    private int amount;
    private final List<Frame> frames;
    private Iterator<Frame> iterator;
    private final Location location;
    private final float rotation;
    private final int frameDelay;
    public FrameSpawnerLocation(int amount, List<Frame> frames, int frameDelay, Location location, float rotation) {
        this.amount = amount;
        this.frames = frames;
        this.iterator = frames.iterator();
        this.location = location;
        this.rotation = rotation;
        this.frameDelay = frameDelay;
    }

    @Override
    public void run() {
        if (amount == 0){
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to end of frames");
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!iterator.hasNext())
                    this.cancel();
                iterator.next().spawn(location, rotation);
            }
        }.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, frameDelay);
        iterator = frames.iterator();
        if (amount != -1)
            amount--;
    }
}
