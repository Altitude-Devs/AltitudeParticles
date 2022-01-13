package com.alttd.gui.actions;

import com.alttd.database.Queries;
import com.alttd.gui.GUIAction;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.PlayerSettings;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class ActivateParticleSet implements GUIAction {

    ParticleSet particleSet;
    Inventory inventory;
    int slot;

    public ActivateParticleSet(ParticleSet particleSet, Inventory inventory, int slot) {
        this.particleSet = particleSet;
        this.inventory = inventory;
        this.slot = slot;
    }

    @Override
    public void click(Player player) {
        boolean enable = false;
        ItemStack itemStack = inventory.getItem(slot);
        if (itemStack != null)
            enable = updateItem(itemStack, player);
        PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
        if (enable)
            playerSettings.addParticle(particleSet.getAPartType(), particleSet);
        else
            playerSettings.removeParticle(particleSet.getAPartType());
    }

    private boolean updateItem(@NotNull ItemStack itemStack, Player player) {
        boolean enable;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasEnchants()) {
            itemMeta.getEnchants()
                    .forEach((enchantment, integer) -> itemMeta.removeEnchant(enchantment));
            Queries.removeParticle(player.getUniqueId(), particleSet.getAPartType());
            enable = false;
        } else {
            Arrays.stream(inventory.getContents())
                    .filter(Objects::nonNull)
                    .forEach(item -> {
                        ItemMeta meta = item.getItemMeta();
                        meta.getEnchants().forEach((enchantment, integer) -> meta.removeEnchant(enchantment));
                        item.setItemMeta(meta);
                    });
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            Queries.addParticle(player.getUniqueId(), particleSet.getAPartType(), particleSet.getParticleId());
            enable = true;
        }
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(slot, itemStack);
        player.updateInventory();
        return enable;
    }
}
