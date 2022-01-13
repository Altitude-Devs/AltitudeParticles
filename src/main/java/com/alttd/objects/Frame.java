package com.alttd.objects;

import com.alttd.storage.PlayerSettings;
import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    List<ParticleBuilder> particles;

    public Frame(List<ParticleBuilder> particles) {
        this.particles = particles;
    }

    /**
     * Spawns all particles in a frame (CALL ASYNC)
     *
     * @param   location  Location to spawn particles at
     */
    public void spawn(Location location) {
        particles.forEach(particleBuilder -> particleBuilder
                .location(location)
                .receivers(Bukkit.getOnlinePlayers().stream()
                        .filter(player -> {
                            PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
                            if (playerSettings == null)
                                return false;
                            return playerSettings.isSeeingParticles();
                        }).collect(Collectors.toList())
                )
                .spawn());
    }
}
