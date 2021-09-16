package com.taylorswiftcn.megumi.uifactory.event;

import lombok.Getter;

@Getter
public enum UIFEvent {

    UIFCompCreateEvent("UIFCompCreateEvent"),
    UIFCompRemoveEvent("UIFCompRemoveEvent"),
    UIFCompPreRenderEvent("UIFCompPreRenderEvent"),
    UIFCompPostRenderEvent("UIFCompPostRenderEvent"),
    UIFCompClickEvent("UIFCompClickEvent"),
    UIFCompClickReleaseEvent("UIFCompClickReleaseEvent"),
    UIFCompMouseEnterEvent("UIFCompMouseEnterEvent"),
    UIFCompMouseLeaveEvent("UIFCompMouseLeaveEvent"),
    UIFCompMouseWheelEvent("UIFCompMouseWheelEvent"),
    UIFCompTextChangeEvent("UIFCompTextChangeEvent"),

    UIFScreenOpenEvent("UIFScreenOpenEvent"),
    UIFScreenCloseEvent("UIFScreenCloseEvent"),
    UIFScreenKeyPressEvent("UIFScreenKeyPressEvent"),
    UIFScreenMouseWheelEvent("UIFScreenMouseWheelEvent"),
    UIFScreenChatOpenEvent("UIFScreenChatOpenEvent"),
    UIFScreenChatCloseEvent("UIFScreenChatCloseEvent"),
    UIFScreenReceiveMsgEvent("UIFScreenReceiveMsgEvent"),
    UIFScreenReloadEvent("UIFScreenReloadEvent");


    private final String name;

    UIFEvent(String name) {
        this.name = name;
    }

    public static UIFEvent getEvent(String name) {
        for (UIFEvent event : values()) {
            if (event.getName().equals(name)) return event;
        }

        return null;
    }
}
