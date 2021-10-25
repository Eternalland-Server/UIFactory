package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFCompEvent;
import com.taylorswiftcn.megumi.uifactory.generate.type.ClickType;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class UIFCompClickReleaseEvent extends UIFCompEvent implements Cancellable {

    @Getter private final ClickType clickType;
    private boolean cancel = false;

    public UIFCompClickReleaseEvent(Player player, String screenID, String compID, ClickType clickType) {
        super(player, screenID, compID);
        this.clickType = clickType;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
