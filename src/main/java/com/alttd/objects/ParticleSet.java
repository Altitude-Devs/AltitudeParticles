package com.alttd.objects;

import com.alttd.AltitudeParticles;
import com.alttd.config.Config;
import com.alttd.frameSpawners.FrameSpawnerLocation;
import com.alttd.frameSpawners.FrameSpawnerPlayer;
import com.alttd.storage.PlayerSettings;
import com.alttd.util.Logger;
import de.myzelyam.api.vanish.VanishAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParticleSet {

    private final List<Frame> frames;
    private final int frameDelay, repeat, repeatDelay;
    private final APartType aPartType;
    private final String uniqueId;
    private final String permission;
    private final ItemStack itemStack;

    public ParticleSet(List<Frame> frames, String name, List<String> lore, int frameDelay, int repeat, int repeatDelay, APartType aPartType, String uniqueId, String permission, ItemStack itemStack) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        this.frames = frames;
        this.frameDelay = frameDelay;
        this.repeat = repeat;
        this.repeatDelay = repeatDelay;
        this.aPartType = aPartType;
        this.uniqueId = uniqueId;
        this.permission = permission;
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize(name));
        itemMeta.lore(lore.stream().map(miniMessage::deserialize).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
    }

    public void run(Location location, Player player) {
        if (tooSoon(player.getUniqueId()) || isVanished(player))
            return;
        FrameSpawnerLocation frameSpawnerLocation = new FrameSpawnerLocation(repeat, repeatDelay, frames, location, player.getLocation().getYaw());
        frameSpawnerLocation.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, frameDelay);
    }

    public void run(Player player, PlayerSettings playerSettings) {
        if (tooSoon(player.getUniqueId()) && !player.hasPermission("apart.bypass-cooldown"))
            return;
        if (Config.DEBUG)
            Logger.info("Starting particle set % for %.", uniqueId, player.getName());
        FrameSpawnerPlayer frameSpawnerPlayer = new FrameSpawnerPlayer(repeat, repeatDelay, frames, player, playerSettings, aPartType, uniqueId);
        frameSpawnerPlayer.runTaskTimerAsynchronously(AltitudeParticles.getInstance(), 0, frameDelay);
    }

    private boolean isVanished(Player player) {
        return VanishAPI.isInvisible(player) || player.getGameMode().equals(GameMode.SPECTATOR);
    }

    private boolean tooSoon(UUID uuid) {
        PlayerSettings ps = PlayerSettings.getPlayer(uuid);
        if (ps.canRun(aPartType))
        {
            ps.run(aPartType);
            return false;
        }
        return true;
    }

    public APartType getAPartType() {
        return aPartType;
    }

    public String getPermission() {
        return permission;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getParticleId() {
        return uniqueId;
    }
}
