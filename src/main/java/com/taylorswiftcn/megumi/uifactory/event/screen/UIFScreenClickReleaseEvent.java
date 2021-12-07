package com.taylorswiftcn.megumi.uifactory.event.screen;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import com.taylorswiftcn.megumi.uifactory.generate.type.ClickType;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class UIFScreenClickReleaseEvent extends UIFBaseEvent {

    private final ClickType clickType;

    public UIFScreenClickReleaseEvent(Player player, String screenID, ClickType clickType) {
        super(player, screenID);
        this.clickType = clickType;
    }
}
