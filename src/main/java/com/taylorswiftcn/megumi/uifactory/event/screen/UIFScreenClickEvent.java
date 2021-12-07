package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import com.taylorswiftcn.megumi.uifactory.generate.type.ClickType;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class UIFScreenClickEvent extends UIFBaseEvent {

    private final String compID;
    private final ClickType clickType;

    public UIFScreenClickEvent(Player player, String screenID, String compID, ClickType clickType) {
        super(player, screenID);
        this.compID = compID;
        this.clickType = clickType;
    }
}
