package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class KillListener implements Listener {
    private final List<APartType> particlesToActivate;

    public KillListener(APartType... particleTypes) {
        particlesToActivate = List.of(particleTypes);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onKill(EntityDeathEvent event) {
        if (event.isCancelled())
            return;
        Player player = event.getEntity().getKiller();
        if (player == null)
            return;
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
                if (!playerSettings.hasActiveParticles())
                    return;
                particlesToActivate.forEach(aPartType -> {
                    ParticleSet particleSet = playerSettings.getParticles(aPartType);
                    if (particleSet == null)
                        return;
                    particleSet.run(event.getEntity().getLocation());
                });
            }
        }.runTaskAsynchronously(AltitudeParticles.getInstance());
    }
}
