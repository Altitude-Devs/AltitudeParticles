package com.alttd.listeners;

import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

public class BlockBreakListener implements Listener {
    private final List<APartType> particlesToActivate;
    public BlockBreakListener(APartType... particleTypes) {
        particlesToActivate = List.of(particleTypes);
    }

    public void onBlockBreak(BlockBreakEvent event) {
        PlayerSettings player = PlayerSettings.getPlayer(event.getPlayer().getUniqueId());
        if (!player.hasActiveParticles())
            return;
        particlesToActivate.forEach(aPartType -> {
            ParticleSet particleSet = player.getParticles(aPartType);
            if (particleSet == null)
                return;
            particleSet.run(event.getBlock().getLocation());
        });
    }
}
