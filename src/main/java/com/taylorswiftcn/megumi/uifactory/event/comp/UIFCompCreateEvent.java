package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFCompCreateEvent extends UIFBaseEvent {

    public UIFCompCreateEvent(Player player, String compID) {
        super(player, compID);
    }

}
