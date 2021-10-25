package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFCompEvent;
import org.bukkit.entity.Player;

public class UIFCompCreateEvent extends UIFCompEvent {

    public UIFCompCreateEvent(Player player, String screenID, String compID) {
        super(player, screenID, compID);
    }

}
