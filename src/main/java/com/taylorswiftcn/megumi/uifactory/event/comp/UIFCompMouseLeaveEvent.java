package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFCompEvent;
import lombok.Getter;
import org.bukkit.entity.Player;

public class UIFCompMouseLeaveEvent extends UIFCompEvent {

    public UIFCompMouseLeaveEvent(Player player, String screenID, String compID) {
        super(player, screenID, compID);
    }

}
