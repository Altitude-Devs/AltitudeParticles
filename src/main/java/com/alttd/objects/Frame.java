package com.alttd.objects;

import com.alttd.storage.PlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Frame {
    List<AParticle> AParticles;

    public Frame(List<AParticle> AParticles) {
        this.AParticles = AParticles;
    }

    /**
     * Spawns all particles in a frame (CALL ASYNC)
     *
     * @param   location  Location to spawn particles at
     */
    public void spawn(Location location) {
        Location tmpLocation = location.clone();
        AParticles.forEach(AParticle -> {
            ThreadLocalRandom current = ThreadLocalRandom.current();
            AParticle.particleBuilder()
                    .location(tmpLocation.set(location.getX() + AParticle.x() + ((AParticle.offset_range() == 0) ? 0 : current.nextDouble(-AParticle.offset_range(), AParticle.offset_range())),
                            location.getY() + AParticle.y() + ((AParticle.offset_range() == 0) ? 0 : current.nextDouble(-AParticle.offset_range(), AParticle.offset_range())),
                            location.getZ() + AParticle.z() + ((AParticle.offset_range() == 0) ? 0 : current.nextDouble(-AParticle.offset_range(), AParticle.offset_range()))))
                    .receivers(Bukkit.getOnlinePlayers().stream()
                            .filter(player -> {
                                PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
                                if (playerSettings == null)
                                    return false;
                                if (!playerSettings.isSeeingParticles())
                                    return false;
                                Location playerLocation = player.getLocation();
                                return location.getWorld().getUID().equals(playerLocation.getWorld().getUID()) && player.getLocation().distance(location) < 100;
                            }).collect(Collectors.toList())
                    )
                    .spawn();
        });
    }
}
