package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class DeathListener implements Listener {

    private final List<APartType> particlesToActivate;

    public DeathListener(APartType... particleTypes) {
        particlesToActivate = List.of(particleTypes);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDeath(PlayerDeathEvent event) {
        if (event.isCancelled())
            return;
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();
                PlayerSettings playerSettings = PlayerSettings.getPlayer(uuid);
                if (!playerSettings.hasActiveParticles())
                    return;
                particlesToActivate.forEach(aPartType -> {
                    ParticleSet particleSet = playerSettings.getParticles(aPartType);
                    if (particleSet == null)
                        return;
                    particleSet.run(player.getLocation(), player);
                });
            }
        }.runTaskAsynchronously(AltitudeParticles.getInstance());
    }
}
