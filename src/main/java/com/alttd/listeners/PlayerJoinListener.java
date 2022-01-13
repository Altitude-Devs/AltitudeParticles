package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.database.Queries;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final List<APartType> particlesToActivate;

    public PlayerJoinListener(APartType... particleTypes) {
        particlesToActivate = List.of(particleTypes);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() { //TODO uncomment
                Player player = event.getPlayer();
                new PlayerSettings(true, true, player.getUniqueId()); //TODO REMOVE
//                UUID uuid = player.getUniqueId();
//                PlayerSettings playerSettings = PlayerSettings.getPlayer(uuid);
//                if (playerSettings == null) Queries.getPlayerSettings(uuid);

                PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
                if (!playerSettings.hasActiveParticles())
                    return;
                particlesToActivate.forEach(aPartType -> {
                    ParticleSet particleSet = playerSettings.getParticles(aPartType);
                    if (particleSet == null)
                        return;
                    particleSet.run(player.getLocation());
                });
            }
        }.runTaskAsynchronously(AltitudeParticles.getInstance());

    }

}
