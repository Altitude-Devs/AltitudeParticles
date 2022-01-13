package com.alttd.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum APartType { //TODO add description?
    HEAD("Head", Material.PLAYER_HEAD, null),
    TRAIL("Trail", Material.GOLD_INGOT, null),
    BREAK_PLACE_BLOCK("Break/place block", Material.GRASS_BLOCK, null),
    DEATH("Death", Material.SKELETON_SKULL, null),
    ATTACK_CLOSE("Attack CQC", Material.DIAMOND_SWORD, null),
    ATTACK_RANGE("Attack ranged", Material.BOW, null),
    TELEPORT_ARRIVE("Teleport", Material.DRAGON_EGG, null);

    private final String name;
    private final Material material;
    private ItemStack itemStack;

    public String getName() {
        return name;
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

    APartType(String name, Material material, ItemStack itemStack) {
        this.name = name;
        this.material = material;
        this.itemStack = itemStack;
    }

}
