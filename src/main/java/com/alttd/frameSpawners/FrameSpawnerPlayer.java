package com.alttd.frameSpawners;

import com.alttd.config.Config;
import com.alttd.objects.APartType;
import com.alttd.objects.Frame;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import com.alttd.util.Logger;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public class FrameSpawnerPlayer extends BukkitRunnable {

    private int amount;
    private final long repeatDelay;
    private final List<Frame> frames;
    private Iterator<Frame> iterator;
    private final Player player;
    private final PlayerSettings playerSettings;
    private final APartType aPartType;
    private final String uniqueId;
    public FrameSpawnerPlayer(int amount, int repeatDelay, List<Frame> frames, Player player, PlayerSettings playerSettings, APartType aPartType, String uniqueId) {
        this.amount = amount;
        this.repeatDelay = (repeatDelay * 1000L) / 20;
        this.frames = frames;
        this.iterator = frames.iterator();
        this.player = player;
        this.playerSettings = playerSettings;
        this.aPartType = aPartType;
        this.uniqueId = uniqueId;
    }

    @Override
    public void run() {
        if (!player.isOnline()) {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to player offline.");
            return;
        }
        ParticleSet activeParticleSet = playerSettings.getParticles(aPartType);
        if (activeParticleSet == null || !activeParticleSet.getParticleId().equalsIgnoreCase(uniqueId)) {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to player switching/disabling particles.");
            return;
        }
        if (iterator.hasNext())
            iterator.next().spawn(player.getLocation());
        else if (amount != 0) {
            iterator = frames.iterator();
            amount--;
            if (repeatDelay <= 0)
                return;
            try { //Wait before repeating the frames
                Thread.sleep(repeatDelay); //TODO figure out why this doesn't work and fix it
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.cancel();
            if (Config.DEBUG)
                Logger.info("Stopped repeating task due to end of frames.");
        }
    }
}
