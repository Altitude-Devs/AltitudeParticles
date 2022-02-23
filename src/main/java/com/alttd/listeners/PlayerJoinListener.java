package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.database.Queries;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final List<APartType> particlesToActivate;

    public PlayerJoinListener(APartType... particleTypes) {
        particlesToActivate = List.of(particleTypes);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();
                PlayerSettings playerSettings = PlayerSettings.getPlayer(uuid);

                if (playerSettings == null)
                    playerSettings = Queries.getPlayerSettings(uuid);

                if (!playerSettings.hasActiveParticles())
                    return;

                PlayerSettings finalPlayerSettings = playerSettings;
                particlesToActivate.forEach(aPartType -> {
                    ParticleSet particleSet = finalPlayerSettings.getParticles(aPartType);
                    if (particleSet == null)
                        return;
                    particleSet.run(player, finalPlayerSettings, uuid);
                });
            }
        }.runTaskAsynchronously(AltitudeParticles.getInstance());
    }

}
