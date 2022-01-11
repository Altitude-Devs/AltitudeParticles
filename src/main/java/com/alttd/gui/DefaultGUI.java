package com.alttd.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class DefaultGUI implements GUI {

    protected final Inventory inventory;
    protected final HashMap<Integer, GUIAction> actions;

    public DefaultGUI(Component name) {
        inventory = Bukkit.createInventory(null, InventoryType.CHEST, name);
        actions = new HashMap<>();
    }

    public void setItem(int slot, @NotNull ItemStack item, @Nullable GUIAction action) {
        inventory.setItem(slot, item);
        if (action != null)
            actions.put(slot, action);
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
        GUIByUUID.put(player.getUniqueId(), this);
    }

    @Override
    public GUIAction getAction(int slot) {
        return actions.get(slot);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Merchant getMerchant() {
        return null;
    }
}
