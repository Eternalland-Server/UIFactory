package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class UIFScreenReceiveMsgEvent extends UIFBaseEvent {

    private final String message;

    public UIFScreenReceiveMsgEvent(Player player, String screenID, String message) {
        super(player, screenID);
        this.message = message;
    }
}
