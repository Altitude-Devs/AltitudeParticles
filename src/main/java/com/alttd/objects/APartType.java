package com.alttd.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum APartType { //TODO add description?
    HEAD("HEAD", "Head", Material.PLAYER_HEAD, null, false),
    TRAIL("TRAIL", "Trail", Material.GOLD_INGOT, null, false),
    BREAK_PLACE_BLOCK("BREAK_PLACE_BLOCK", "Break/place block", Material.GRASS_BLOCK, null, true),
    DEATH("DEATH", "Death", Material.SKELETON_SKULL, null, true),
    KILL("KILL", "Kill", Material.DIAMOND_SWORD, null, true),
    CLICK_BLOCK("CLICK_BLOCK", "Right click block", Material.DIAMOND_BLOCK, null, true),
    TELEPORT_ARRIVE("TELEPORT", "Teleport", Material.DRAGON_EGG, null, true);

    private final String name;
    private final String displayName;
    private final Material material;
    private ItemStack itemStack;
    private final boolean event;

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public boolean hasEvent() {
        return event;
    }

    APartType(String name, String displayName, Material material, ItemStack itemStack, boolean event) {
        this.name = name;
        this.displayName = displayName;
        this.material = material;
        this.itemStack = itemStack;
        this.event = event;
    }

}
