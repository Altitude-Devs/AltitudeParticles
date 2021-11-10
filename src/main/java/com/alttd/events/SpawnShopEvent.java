package com.alttd.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public final class SpawnShopEvent extends Event {
    private final Player player;
    private final int amount;
    private final double price;
    private final Material item;
    private final int pointsBefore;
    private final int pointsAfter;
    private final boolean buy;

    public SpawnShopEvent(Player player, int amount, double price, Material item,
                          int pointsBefore, int pointsAfter, boolean buy) {
        this.player = player;
        this.amount = amount;
        this.price = price;
        this.item = item;
        this.pointsBefore = pointsBefore;
        this.pointsAfter = pointsAfter;
        this.buy = buy;
    }

    public Player player() {
        return player;
    }

    public int amount() {
        return amount;
    }

    public double price() {
        return price;
    }

    public Material item() {
        return item;
    }

    public int pointsBefore() {
        return pointsBefore;
    }

    public int pointsAfter() {
        return pointsAfter;
    }

    public boolean buy() {
        return buy;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return null;
    }
}
