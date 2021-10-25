package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFCompEvent;
import lombok.Getter;
import org.bukkit.entity.Player;

public class UIFCompMouseWheelEvent extends UIFCompEvent {

    @Getter private final int value;

    public UIFCompMouseWheelEvent(Player player, String screenID, String compID, int value) {
        super(player, screenID, compID);
        this.value = value;
    }

}
