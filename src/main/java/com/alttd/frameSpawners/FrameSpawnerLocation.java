package com.alttd.frameSpawners;

import com.alttd.config.Config;
import com.alttd.objects.Frame;
import com.alttd.util.Logger;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public class FrameSpawnerLocation extends BukkitRunnable {

    private int amount;
    private final long repeatDelay;
    private final List<Frame> frames;
    private Iterator<Frame> iterator;
    private final Location location;
    private final float rotation;
    public FrameSpawnerLocation(int amount, int repeatDelay, List<Frame> frames, Location location, float rotation) {
        this.amount = amount;
        this.repeatDelay = (repeatDelay * 1000L) / 20;
        this.frames = frames;
        this.iterator = frames.iterator();
        this.location = location;
        this.rotation = rotation;
    }

    @Override
    public void run() {
        if (iterator.hasNext())
            iterator.next().spawn(location, rotation);
        else if (amount != 0) {
            iterator = frames.iterator();
            if (amount > 0)
                amount--;
        } else {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to end of frames");
        }
    }
}
