package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFCompEvent;
import lombok.Getter;
import org.bukkit.entity.Player;

public class UIFCompTextChangeEvent extends UIFCompEvent {

    @Getter private final String text;

    public UIFCompTextChangeEvent(Player player, String screenID, String compID, String text) {
        super(player, screenID, compID);
        this.text = text;
    }
}
