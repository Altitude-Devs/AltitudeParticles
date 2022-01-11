package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.database.Queries;
import com.alttd.storage.PlayerSettings;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                UUID uuid = event.getPlayer().getUniqueId();
                PlayerSettings playerSettings = PlayerSettings.getPlayer(uuid);
                if (playerSettings == null) Queries.getPlayerSettings(uuid);

                //TODO activate particles sync/async???
            }
        }.runTaskAsynchronously(AltitudeParticles.getInstance());
    }

}
