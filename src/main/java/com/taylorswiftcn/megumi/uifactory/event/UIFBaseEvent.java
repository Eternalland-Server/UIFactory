package com.taylorswiftcn.megumi.uifactory.event;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public abstract class UIFBaseEvent extends Event {

    private final Player player;
    private final String id;

    private static final HandlerList handlerList = new HandlerList();

    public UIFBaseEvent(Player player, String id) {
        this.player = player;
        this.id = id;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public void call() {
        Bukkit.getPluginManager().callEvent(this);
    }
}
