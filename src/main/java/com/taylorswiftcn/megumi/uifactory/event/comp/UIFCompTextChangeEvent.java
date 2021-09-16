package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFCompTextChangeEvent extends UIFBaseEvent {

    private final String text;

    public UIFCompTextChangeEvent(Player player, String compID, String text) {
        super(player, compID);
        this.text = text;
    }
}
