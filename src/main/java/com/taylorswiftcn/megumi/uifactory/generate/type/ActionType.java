package com.taylorswiftcn.megumi.uifactory.generate.type;

import lombok.Getter;

@Getter
public enum ActionType {

    Create("create", "UIFCreateEvent"),
    Remove("remove", "UIFRemoveEvent"),
    PreRender("preRender", "UIFPreRenderEvent"),
    PostRender("postRender", "UIFPostRenderEvent"),
    Left_Click("click_left", "UIFClickEvent", String.format("'%s'", ClickType.LEFT.getName())),
    Right_Click("click_right", "UIFClickEvent", String.format("'%s'", ClickType.RIGHT.getName())),
    Middle_Click("click_middle", "UIFClickEvent", String.format("'%s'", ClickType.MIDDLE.getName())),
    Click("click", "UIFClickEvent"),
    Left_Release("release_left", "UIFClickReleaseEvent", String.format("'%s'", ClickType.LEFT.getName())),
    Right_Release("release_right", "UIFClickReleaseEvent", String.format("'%s'", ClickType.RIGHT.getName())),
    Middle_Release("release_middle", "UIFClickReleaseEvent", String.format("'%s'", ClickType.MIDDLE.getName())),
    Release("release", "UIFClickReleaseEvent"),
    Enter("enter", "UIFMouseEnterEvent"),
    Leave("leave", "UIFMouseLeaveEvent"),
    Wheel("wheel", "UIFMouseWheelEvent", "方法.取滚轮值"),
    TextChange("TextChange", "UIFTextChangeEvent");

    private final String name;
    private final String event;
    private final String param;

    ActionType(String name, String event) {
        this.name = name;
        this.event = event;
        this.param = null;
    }

    ActionType(String name, String event, String param) {
        this.name = name;
        this.event = event;
        this.param = param;
    }
}
