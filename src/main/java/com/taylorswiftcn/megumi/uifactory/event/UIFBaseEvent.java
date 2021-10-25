package com.taylorswiftcn.megumi.uifactory.event;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

@Getter
public abstract class UIFBaseEvent extends PlayerEvent {

    private final String screenID;

    private static final HandlerList handlerList = new HandlerList();

    public UIFBaseEvent(Player player, String screenID) {
        super(player);
        this.screenID = screenID;
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
