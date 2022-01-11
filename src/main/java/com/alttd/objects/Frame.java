package com.alttd.objects;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

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
        particles.forEach(particleBuilder -> particleBuilder.location(location).spawn());
    }
}
