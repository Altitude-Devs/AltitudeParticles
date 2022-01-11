package com.alttd.frameSpawners;

import com.alttd.config.Config;
import com.alttd.objects.Frame;
import com.alttd.util.Logger;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public class FrameSpawnerPlayer extends BukkitRunnable {

    int amount;
    List<Frame> frames;
    Iterator<Frame> iterator;
    Player player;
    public FrameSpawnerPlayer(int amount, List<Frame> frames, Player player) {
        this.amount = amount;
        this.frames = frames;
        this.iterator = frames.iterator();
        this.player = player;
    }

    @Override
    public void run() {
        if (!player.isOnline()) {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to player offline.");
            return;
        }
        if (iterator.hasNext())
            iterator.next().spawn(player.getLocation());
        else if (amount != 0)
            iterator = frames.iterator();
        else {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to end of frames.");
        }
    }
}
