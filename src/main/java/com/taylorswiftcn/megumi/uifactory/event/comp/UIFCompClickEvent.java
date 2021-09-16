package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import com.taylorswiftcn.megumi.uifactory.generate.type.ClickType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;


public class UIFCompClickEvent extends UIFBaseEvent implements Cancellable {

    private final ClickType clickType;
    private boolean cancel = false;

    public UIFCompClickEvent(Player player, String compID, ClickType clickType) {
        super(player, compID);
        this.clickType = clickType;
    }

    public ClickType getClickType() {
        return clickType;
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
