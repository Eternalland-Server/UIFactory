package com.taylorswiftcn.megumi.uifactory.generate.type;

import com.taylorswiftcn.megumi.uifactory.event.UIFEvent;
import lombok.Getter;

@Getter
public enum FunctionType {

    Open("open", UIFEvent.UIFScreenOpenEvent),
    Close("close", UIFEvent.UIFScreenCloseEvent),
    KeyPress("keyPress", UIFEvent.UIFScreenKeyPressEvent, "func.key_get_press"),
    Wheel("wheel", UIFEvent.UIFScreenMouseWheelEvent, "func.mouse_get_wheel"),
    ChatOpen("chatOpen", UIFEvent.UIFScreenChatOpenEvent),
    ChatClose("chatClose", UIFEvent.UIFScreenChatCloseEvent),
    Message("message", UIFEvent.UIFScreenReceiveMsgEvent, "func.message_current"),
    Reload("reload", UIFEvent.UIFScreenReloadEvent),

    Click("click", UIFEvent.UIFScreenClickEvent, String.format("func.Component_Hovered_Name, '%s'", ClickType.ANY.getName())),
    Left_Click("click_left", UIFEvent.UIFScreenClickEvent, String.format("func.Component_Hovered_Name, '%s'", ClickType.LEFT.getName())),
    Right_Click("click_right", UIFEvent.UIFScreenClickEvent, String.format("func.Component_Hovered_Name, '%s'", ClickType.RIGHT.getName())),

    Release("release", UIFEvent.UIFScreenClickReleaseEvent, String.format("'%s'", ClickType.ANY.getName())),
    Left_Release("release_left", UIFEvent.UIFScreenClickReleaseEvent, String.format("'%s'", ClickType.RIGHT.getName())),
    Right_Release("release_right", UIFEvent.UIFScreenClickReleaseEvent, String.format("'%s'", ClickType.MIDDLE.getName()));


    private final String name;
    private final UIFEvent event;
    private final String param;

    FunctionType(String name, UIFEvent event) {
        this.name = name;
        this.event = event;
        this.param = null;
    }

    FunctionType(String name, UIFEvent event, String param) {
        this.name = name;
        this.event = event;
        this.param = param;
    }
}
