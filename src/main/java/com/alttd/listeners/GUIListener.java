package com.alttd.listeners;

import com.alttd.AltitudeParticles;
import com.alttd.gui.GUI;
import com.alttd.gui.GUIAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIListener implements Listener {

    /**
     * Handles clicking inside a gui
     * @param event gui click event
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onClick(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player player)){
            return;
        }

        GUI gui = GUI.GUIByUUID.get(player.getUniqueId());
        if (gui == null || gui.getInventory() == null)
            return;
        if (!gui.getInventory().equals(event.getInventory()))
            return;
        event.setCancelled(true);
        GUIAction action = gui.getAction(event.getSlot());

        new BukkitRunnable() {
            @Override
            public void run() {
                if (action != null){
                    action.click(player);
                }
            }
        }.runTaskAsynchronously(AltitudeParticles.getInstance());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        GUI.GUIByUUID.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        GUI.GUIByUUID.remove(event.getPlayer().getUniqueId());
    }
}
