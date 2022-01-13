package com.alttd.listeners;

import com.alttd.storage.PlayerSettings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        PlayerSettings.removePlayer(event.getPlayer().getUniqueId());
    }
}
