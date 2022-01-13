package com.alttd.frameSpawners;

import com.alttd.config.Config;
import com.alttd.objects.Frame;
import com.alttd.util.Logger;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public class FrameSpawnerLocation extends BukkitRunnable {

    int amount;
    List<Frame> frames;
    Iterator<Frame> iterator;
    Location location;
    public FrameSpawnerLocation(int amount, List<Frame> frames, Location location) {
        this.amount = amount;
        this.frames = frames;
        this.iterator = frames.iterator();
        this.location = location;
    }

    @Override
    public void run() {
        if (iterator.hasNext())
            iterator.next().spawn(location);
        else if (amount != 0) {
            iterator = frames.iterator();
            amount--;
        } else {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to end of frames");
        }
    }
}
