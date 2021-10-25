package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFCompEvent;
import org.bukkit.entity.Player;

public class UIFCompRemoveEvent extends UIFCompEvent {

    public UIFCompRemoveEvent(Player player, String screenID, String compID) {
        super(player, screenID, compID);
    }

}
