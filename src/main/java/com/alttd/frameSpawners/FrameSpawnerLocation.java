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
    public FrameSpawnerLocation(int amount, int repeatDelay, List<Frame> frames, Location location) {
        this.amount = amount;
        this.repeatDelay = (repeatDelay * 1000L) / 20;
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
            if (repeatDelay <= 0)
                return;
            try { //Wait before repeating the frames
                Thread.sleep(repeatDelay); //TODO figure out why this isn't working and fix it
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to end of frames");
        }
    }
}
