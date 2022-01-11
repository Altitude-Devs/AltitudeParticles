package com.alttd.storage;

import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;

import java.util.HashMap;
import java.util.UUID;

public class PlayerSettings {

    private static final HashMap<UUID, PlayerSettings> playerSettingsMap = new HashMap<>();

    private boolean particlesActive, seeingParticles;
    private final UUID uuid;
    private final HashMap<APartType, ParticleSet> particles;

    public PlayerSettings(boolean particlesActive, boolean seeingParticles, UUID uuid, HashMap<APartType, ParticleSet> particles) {
        this.particlesActive = particlesActive;
        this.seeingParticles = seeingParticles;
        this.uuid = uuid;
        this.particles = particles;

        playerSettingsMap.put(uuid, this);
    }

    public PlayerSettings(boolean particlesActive, boolean seeingParticles, UUID uuid) {
        this.particlesActive = particlesActive;
        this.seeingParticles = seeingParticles;
        this.uuid = uuid;
        this.particles = new HashMap<>();

        playerSettingsMap.put(uuid, this);
    }

    public boolean hasActiveParticles() {
        return particlesActive;
    }

    public void toggleParticlesActive() {
        particlesActive = !particlesActive;
    }

    public boolean isSeeingParticles() {
        return seeingParticles;
    }

    public void toggleSeeingParticles() {
        seeingParticles = !seeingParticles;
    }

    public UUID getUuid() {
        return uuid;
    }

    public HashMap<APartType, ParticleSet> getParticles() {
        return particles;
    }

    public ParticleSet getParticles(APartType aPartType) {
        return particles.get(aPartType);
    }

    public static PlayerSettings getPlayer(UUID uuid) {
        return playerSettingsMap.get(uuid);
    }
}
