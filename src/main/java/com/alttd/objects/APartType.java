package com.alttd.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public enum APartType { //TODO add description?
    HEAD("HEAD", "Head", Material.PLAYER_HEAD, 0,null, false),
    TRAIL("TRAIL", "Trail", Material.GOLD_INGOT, 0, null, false),
    BREAK_PLACE_BLOCK("BREAK_PLACE_BLOCK", "Break/place block", Material.GRASS_BLOCK, TimeUnit.SECONDS.toMillis(5), null, true),
    DEATH("DEATH", "Death", Material.SKELETON_SKULL, TimeUnit.SECONDS.toMillis(30), null, true),
    KILL("KILL", "Kill", Material.DIAMOND_SWORD, TimeUnit.SECONDS.toMillis(5), null, true),
    CLICK_BLOCK("CLICK_BLOCK", "Right click block", Material.DIAMOND_BLOCK, TimeUnit.SECONDS.toMillis(60), null, true),
    TELEPORT_ARRIVE("TELEPORT", "Teleport", Material.DRAGON_EGG, TimeUnit.SECONDS.toMillis(5), null, true);

    private final String name;
    private final String displayName;
    private final Material material;
    private final long delay;
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

    public long getDelay() {
        return delay;
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

    APartType(String name, String displayName, Material material, long delay, ItemStack itemStack, boolean event) {
        this.name = name;
        this.displayName = displayName;
        this.material = material;
        this.delay = delay;
        this.itemStack = itemStack;
        this.event = event;
    }
}
