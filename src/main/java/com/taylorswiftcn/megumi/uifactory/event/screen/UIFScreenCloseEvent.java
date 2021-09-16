package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFScreenCloseEvent extends UIFBaseEvent {

    public UIFScreenCloseEvent(Player player, String screenID) {
        super(player, screenID);
    }

}
