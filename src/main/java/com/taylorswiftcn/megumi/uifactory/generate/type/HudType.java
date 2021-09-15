package com.taylorswiftcn.megumi.uifactory.generate.type;

import lombok.Getter;

@Getter
public enum HudType {

    Helmet("HELMET"),
    Portal("PORTAL"),
    CrossHairs("CROSSHAIRS"),
    BossHealth("BOSSHEALTH"),
    BossInfo("BOSSINFO"),
    Armor("ARMOR"),
    Health("HEALTH"),
    Food("FOOD"),
    AIR("AIR"),
    HotBar("HOTBAR"),
    Experience("EXPERIENCE"),
    Text("TEXT"),
    HealthMount("HEALTHMOUNT"),
    JumpBar("JUMPBAR"),
    Chat("CHAT"),
    PlayerList("PLAYER_LIST"),
    Debug("DEBUG"),
    PotionIcons("PotionIcons"),
    Subtitles("SUBTITLES"),
    FPSGraph("FPS_GRAPH"),
    Vignette("VIGNETTE");

    private final String name;

    HudType(String name) {
        this.name = name;
    }
}
