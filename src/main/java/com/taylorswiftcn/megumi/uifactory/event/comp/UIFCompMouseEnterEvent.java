package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFCompMouseEnterEvent extends UIFBaseEvent {

    public UIFCompMouseEnterEvent(Player player, String compID) {
        super(player, compID);
    }

}
