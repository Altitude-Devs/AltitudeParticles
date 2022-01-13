package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TeleportArriveListener implements Listener {
    private final List<APartType> particlesToActivate;

    public TeleportArriveListener(APartType... particleTypes) {
        particlesToActivate = List.of(particleTypes);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onTeleportArrive(PlayerTeleportEvent event) {
        if (event.isCancelled())
            return;
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
                if (!playerSettings.hasActiveParticles())
                    return;
                particlesToActivate.forEach(aPartType -> {
                    ParticleSet particleSet = playerSettings.getParticles(aPartType);
                    if (particleSet == null)
                        return;
                    particleSet.run(event.getTo());
                });
            }
        }.runTaskAsynchronously(AltitudeParticles.getInstance());
    }

}
