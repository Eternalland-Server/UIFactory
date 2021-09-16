package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFScreenOpenEvent extends UIFBaseEvent {

    public UIFScreenOpenEvent(Player player, String screenID) {
        super(player, screenID);
    }

}
