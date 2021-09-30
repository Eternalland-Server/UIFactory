package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import com.taylorswiftcn.megumi.uifactory.generate.type.SubmitParams;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class UIFCompSubmitEvent extends UIFBaseEvent {

    private final SubmitParams params;

    public UIFCompSubmitEvent(Player player, String id, SubmitParams params) {
        super(player, id);
        this.params = params;
    }
}
