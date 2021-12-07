package com.taylorswiftcn.megumi.uifactory.generate.type;

import com.taylorswiftcn.megumi.uifactory.event.UIFEvent;
import lombok.Getter;

@Getter
public enum ActionType {

    Create("create", UIFEvent.UIFCompCreateEvent),
    Remove("remove", UIFEvent.UIFCompRemoveEvent),
    PreRender("preRender", UIFEvent.UIFCompPreRenderEvent),
    PostRender("postRender", UIFEvent.UIFCompPostRenderEvent),

    Click("click", UIFEvent.UIFCompClickEvent, String.format("'%s'", ClickType.ANY.getName())),
    Left_Click("click_left", UIFEvent.UIFCompClickEvent, String.format("'%s'", ClickType.LEFT.getName())),
    Right_Click("click_right", UIFEvent.UIFCompClickEvent, String.format("'%s'", ClickType.RIGHT.getName())),
    Middle_Click("click_middle", UIFEvent.UIFCompClickEvent, String.format("'%s'", ClickType.MIDDLE.getName())),

    Release("release", UIFEvent.UIFCompClickReleaseEvent, String.format("'%s'", ClickType.ANY.getName())),
    Left_Release("release_left", UIFEvent.UIFCompClickReleaseEvent, String.format("'%s'", ClickType.LEFT.getName())),
    Right_Release("release_right", UIFEvent.UIFCompClickReleaseEvent, String.format("'%s'", ClickType.RIGHT.getName())),
    Middle_Release("release_middle", UIFEvent.UIFCompClickReleaseEvent, String.format("'%s'", ClickType.MIDDLE.getName())),

    Enter("enter", UIFEvent.UIFCompMouseEnterEvent),
    Leave("leave", UIFEvent.UIFCompMouseLeaveEvent),
    Wheel("wheel", UIFEvent.UIFCompMouseWheelEvent, "func.mouse_get_wheel"),
    TextChange("textChange", UIFEvent.UIFCompTextChangeEvent, "%comp%.text");

    private final String name;
    private final UIFEvent event;
    private final String param;

    ActionType(String name, UIFEvent event) {
        this.name = name;
        this.event = event;
        this.param = null;
    }

    ActionType(String name, UIFEvent event, String param) {
        this.name = name;
        this.event = event;
        this.param = param;
    }
}
