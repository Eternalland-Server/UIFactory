package com.taylorswiftcn.megumi.uifactory.generate.type;

import lombok.Getter;

@Getter
public enum ClickType {

    ANY("ANY"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    MIDDLE("MIDDLE");

    private final String name;

    ClickType(String name) {
        this.name = name;
    }
}
