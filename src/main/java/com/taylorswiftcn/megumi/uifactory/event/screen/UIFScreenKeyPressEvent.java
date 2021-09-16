package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class UIFScreenKeyPressEvent extends UIFBaseEvent {

    private final String key;

    public UIFScreenKeyPressEvent(Player player, String screenID, String key) {
        super(player, screenID);
        this.key = key;
    }
}
