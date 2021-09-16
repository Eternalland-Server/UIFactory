package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFScreenChatCloseEvent extends UIFBaseEvent {

    public UIFScreenChatCloseEvent(Player player, String screenID) {
        super(player, screenID);
    }

}
