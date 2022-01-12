package com.alttd.storage;

import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParticleStorage {
    private static final HashMap<APartType, List<ParticleSet>> particles = new HashMap<>();

    public static void addParticleSet(APartType aPartType, ParticleSet particleSet) {
        List<ParticleSet> particleSets = particles.getOrDefault(aPartType, new ArrayList<>());
        if (particleSets.contains(particleSet))
            return;
        particleSets.add(particleSet);
        particles.put(aPartType, particleSets);
    }

    public static List<ParticleSet> getParticleSets(APartType aPartType) {
        return particles.getOrDefault(aPartType, new ArrayList<>());
    }
}
