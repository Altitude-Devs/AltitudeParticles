package com.alttd.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.Merchant;

import java.util.HashMap;
import java.util.UUID;

public interface GUI {
    HashMap<UUID, GUI> GUIByUUID = new HashMap<>();

    void open(Player player);

    com.alttd.gui.GUIAction getAction(int slot);

    Inventory getInventory();

    Merchant getMerchant();
}
