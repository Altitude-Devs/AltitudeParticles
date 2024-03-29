package com.alttd.storage;

import com.alttd.database.Queries;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PlayerSettings {

    private static final HashMap<UUID, PlayerSettings> playerSettingsMap = new HashMap<>();

    private boolean particlesActive, seeingParticles;
    private final UUID uuid;
    private final HashMap<APartType, ParticleSet> particles;
    private final HashMap<APartType, Long> lastUsed;

    public PlayerSettings(boolean particlesActive, boolean seeingParticles, UUID uuid, HashMap<APartType, ParticleSet> particles) {
        this.particlesActive = particlesActive;
        this.seeingParticles = seeingParticles;
        this.uuid = uuid;
        this.particles = particles;

        playerSettingsMap.put(uuid, this);
        lastUsed = new HashMap<>();
    }

    public PlayerSettings(boolean particlesActive, boolean seeingParticles, UUID uuid) {
        this.particlesActive = particlesActive;
        this.seeingParticles = seeingParticles;
        this.uuid = uuid;
        this.particles = new HashMap<>();

        playerSettingsMap.put(uuid, this);
        lastUsed = new HashMap<>();
    }

    public static PlayerSettings getPlayer(UUID uuid) {
        return playerSettingsMap.get(uuid);
    }

    public static void removePlayer(UUID uuid) {
        playerSettingsMap.remove(uuid);
    }

    public boolean hasActiveParticles() {
        return particlesActive;
    }

    public boolean toggleParticlesActive() {
        particlesActive = !particlesActive;
        return particlesActive;
    }

    public boolean isSeeingParticles() {
        return seeingParticles;
    }

    public boolean toggleSeeingParticles() {
        seeingParticles = !seeingParticles;
        return seeingParticles;
    }

    public UUID getUuid() {
        return uuid;
    }

    public HashMap<APartType, ParticleSet> getParticles() {
        return particles;
    }

    public void addParticle(APartType aPartType, ParticleSet particleSet) {
        particles.put(aPartType, particleSet);
    }

    public void removeParticle(APartType aPartType) {
        particles.remove(aPartType);
    }

    public ParticleSet getParticles(APartType aPartType) {
        return particles.get(aPartType);
    }

    public void clearParticles() {
        particles.clear();
        Queries.clearParticles(uuid);
    }

    public boolean canRun(APartType aPartType) {
        if (!lastUsed.containsKey(aPartType))
            return true;
        return (lastUsed.get(aPartType) < new Date().getTime() - aPartType.getDelay());
    }

    public void run(APartType aPartType) {
        lastUsed.put(aPartType, new Date().getTime());
    }
}
