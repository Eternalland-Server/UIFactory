package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFScreenReloadEvent extends UIFBaseEvent {

    public UIFScreenReloadEvent(Player player, String id) {
        super(player, id);
    }

}
