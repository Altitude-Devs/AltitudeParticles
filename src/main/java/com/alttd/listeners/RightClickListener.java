package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class RightClickListener implements Listener {

    private final List<APartType> particlesToActivate;

    public RightClickListener(APartType... particleTypes) {
        particlesToActivate = List.of(particleTypes);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEmptyRightClick(PlayerInteractEvent event) {
        EquipmentSlot hand = event.getHand();
        if (hand == null || !hand.equals(EquipmentSlot.OFF_HAND) || !event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;
        ItemStack itemStack = event.getItem();
        if (itemStack != null && !itemStack.getType().equals(Material.AIR))
            return;
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null)
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
