package com.taylorswiftcn.megumi.uifactory.generate.type;

import lombok.Getter;

@Getter
public enum EntityViewType {

    Owner("owner"),
    FirstAim("firstaim");

    private final String name;

    EntityViewType(String name) {
        this.name = name;
    }
}
