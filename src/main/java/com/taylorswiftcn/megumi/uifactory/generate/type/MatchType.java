package com.taylorswiftcn.megumi.uifactory.generate.type;

import lombok.Getter;

@Getter
public enum MatchType {

    HUD("hud"),
    HUD_POST("hud_post"),
    GuiInventory("GuiInventory"),
    EscUI("GuiIngameMenu");

    private final String name;

    MatchType(String name) {
        this.name = name;
    }
}
