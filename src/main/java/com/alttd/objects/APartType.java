package com.alttd.objects;

import com.alttd.config.Config;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

public enum APartType { //TODO add description?
    HEAD("HEAD", "Head", Material.PLAYER_HEAD, TimeUnit.SECONDS.toMillis(Config.HEAD_COOL_DOWN),null, false),
    TRAIL("TRAIL", "Trail", Material.GOLD_INGOT, TimeUnit.SECONDS.toMillis(Config.TRAIL_COOL_DOWN), null, false),
    BREAK_PLACE_BLOCK("BREAK_PLACE_BLOCK", "Break/place block", Material.GRASS_BLOCK, TimeUnit.SECONDS.toMillis(Config.BREAK_PLACE_BLOCK_COOL_DOWN), null, true),
    DEATH("DEATH", "Death", Material.SKELETON_SKULL, TimeUnit.SECONDS.toMillis(Config.DEATH_COOL_DOWN), null, true),
    KILL("KILL", "Kill", Material.DIAMOND_SWORD, TimeUnit.SECONDS.toMillis(Config.KILL_COOL_DOWN), null, true),
    CLICK_BLOCK("CLICK_BLOCK", "Right click block", Material.DIAMOND_BLOCK, TimeUnit.SECONDS.toMillis(Config.CLICK_BLOCK_COOL_DOWN), null, true),
    TELEPORT_ARRIVE("TELEPORT", "Teleport", Material.DRAGON_EGG, TimeUnit.SECONDS.toMillis(Config.TELEPORT_ARRIVE_COOL_DOWN), null, true);

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
