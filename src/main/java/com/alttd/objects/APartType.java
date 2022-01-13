package com.alttd.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum APartType { //TODO add description?
    HEAD("HEAD", "Head", Material.PLAYER_HEAD, null),
    TRAIL("TRAIL", "Trail", Material.GOLD_INGOT, null),
    BREAK_PLACE_BLOCK("BREAK_PLACE_BLOCK", "Break/place block", Material.GRASS_BLOCK, null),
    DEATH("DEATH", "Death", Material.SKELETON_SKULL, null),
    ATTACK_CLOSE("ATTACK_CQC", "Attack CQC", Material.DIAMOND_SWORD, null),
    ATTACK_RANGE("ATTACK_RANGED", "Attack ranged", Material.BOW, null),
    TELEPORT_ARRIVE("TELEPORT", "Teleport", Material.DRAGON_EGG, null);

    private final String name;
    private final String displayName;
    private final Material material;
    private ItemStack itemStack;

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

    APartType(String name, String displayName, Material material, ItemStack itemStack) {
        this.name = name;
        this.displayName = displayName;
        this.material = material;
        this.itemStack = itemStack;
    }

}
