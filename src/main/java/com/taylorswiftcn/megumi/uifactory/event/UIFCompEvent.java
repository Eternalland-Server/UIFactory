package com.taylorswiftcn.megumi.uifactory.event;

import lombok.Getter;
import org.bukkit.entity.Player;

public abstract class UIFCompEvent extends UIFBaseEvent {

    @Getter private final String compID;

    public UIFCompEvent(Player player, String screenID, String compID) {
        super(player, screenID);
        this.compID = compID;
    }
}
