package com.alttd.gui.windows;

import com.alttd.AltitudeParticles;
import com.alttd.config.Config;
import com.alttd.gui.DefaultGUI;
import com.alttd.gui.actions.ActivateParticleSet;
import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.ParticleStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

public class ChooseParticleGUI extends DefaultGUI {

    static ItemStack backButton;

    static {
        MiniMessage miniMessage = MiniMessage.miniMessage();

        backButton = new ItemStack(Material.OAK_DOOR);
        ItemMeta itemMeta = backButton.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize(Config.BACK_BUTTON));
        backButton.setItemMeta(itemMeta);
    }

    public ChooseParticleGUI(APartType aPartType, Component name, Player player) {
        super(name);
        List<ParticleSet> availableParticles = ParticleStorage.getParticleSets(aPartType).stream()
                .filter(particleSet -> player.hasPermission(particleSet.getPermission()))
                .collect(Collectors.toList());

        int i = 0;
        for (ParticleSet particleSet : availableParticles) {
            if (i >= 25) //leave the last 2 slots of the inventory open
                return;
            setItem(i++, particleSet.getItemStack(), new ActivateParticleSet(particleSet));
        }

        setItem(26, backButton, clickingPlayer -> new BukkitRunnable() {
            @Override
            public void run() {
                new OpenParticleGUI(clickingPlayer).open(clickingPlayer);
            }
        }.runTaskLater(AltitudeParticles.getInstance(), 0));
    }
}
