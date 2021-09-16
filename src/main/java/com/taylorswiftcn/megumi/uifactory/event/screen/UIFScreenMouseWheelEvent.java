package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class UIFScreenMouseWheelEvent extends UIFBaseEvent {

    private final int value;

    public UIFScreenMouseWheelEvent(Player player, String screenID, int value) {
        super(player, screenID);
        this.value = value;
    }
}
