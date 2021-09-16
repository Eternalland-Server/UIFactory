package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import org.bukkit.entity.Player;

public class UIFCompMouseWheelEvent extends UIFBaseEvent {

    private final int value;

    public UIFCompMouseWheelEvent(Player player, String compID, int value) {
        super(player, compID);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
