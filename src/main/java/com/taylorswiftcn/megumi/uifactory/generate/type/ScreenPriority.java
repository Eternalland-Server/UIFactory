package com.taylorswiftcn.megumi.uifactory.generate.type;

public enum ScreenPriority {

    LOWEST(1),
    LOW(2),
    NORMAL(3),
    HIGH(4),
    HIGHEST(5);

    private Integer index;

    ScreenPriority(int index) {
        this.index = index;
    }
}
