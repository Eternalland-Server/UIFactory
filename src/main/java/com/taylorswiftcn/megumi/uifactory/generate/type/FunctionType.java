package com.taylorswiftcn.megumi.uifactory.generate.type;

import lombok.Getter;

@Getter
public enum FunctionType {

    Open("open", "UIFScreenOpenEvent"),
    Close("close", "UIFScreenCloseEvent"),
    KeyPress("keyPress", "UIFScreenKeyPressEvent", "func.key_get_press"),
    Wheel("wheel", "UIFScreenMouseWheelEvent", "func.mouse_get_wheel"),
    ChatOpen("chatOpen", "UIFScreenChatOpenEvent"),
    ChatClose("chatClose", "UIFScreenChatCloseEvent"),
    Message("message", "UIFScreenReceiveMsgEvent"),
    Reload("reload", "UIFScreenReloadEvent");

    private String name;
    private String event;
    private String param;

    FunctionType(String name, String event) {
        this.name = name;
        this.event = event;
    }

    FunctionType(String name, String event, String param) {
        this.name = name;
        this.event = event;
        this.param = param;
    }
}
