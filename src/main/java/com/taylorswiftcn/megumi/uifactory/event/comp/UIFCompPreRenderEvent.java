package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFCompPreRenderEvent extends UIFBaseEvent {

    public UIFCompPreRenderEvent(Player player, String compID) {
        super(player, compID);
    }

}
