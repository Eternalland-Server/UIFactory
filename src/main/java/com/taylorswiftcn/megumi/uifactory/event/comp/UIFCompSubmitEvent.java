package com.taylorswiftcn.megumi.uifactory.event.comp;

import com.taylorswiftcn.megumi.uifactory.event.UIFCompEvent;
import com.taylorswiftcn.megumi.uifactory.generate.function.SubmitParams;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class UIFCompSubmitEvent extends UIFCompEvent {

    @Getter private final SubmitParams params;

    public UIFCompSubmitEvent(Player player, String screenID, String compID, SubmitParams params) {
        super(player, screenID, compID);
        this.params = params;
    }
}
