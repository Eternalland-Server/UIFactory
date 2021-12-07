package com.taylorswiftcn.megumi.uifactory.listener;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import com.taylorswiftcn.megumi.uifactory.event.UIFEvent;
import com.taylorswiftcn.megumi.uifactory.event.comp.*;
import com.taylorswiftcn.megumi.uifactory.event.screen.*;
import com.taylorswiftcn.megumi.uifactory.generate.type.ClickType;
import com.taylorswiftcn.megumi.uifactory.generate.function.SubmitParams;
import net.sakuragame.eternal.dragoncore.api.gui.event.CustomPacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.LinkedList;
import java.util.List;

public class PacketListener implements Listener {

    @EventHandler
    public void onReceivePacket(CustomPacketEvent e) {
        Player player = e.getPlayer();
        String eventName = e.getIdentifier();
        List<String> data = e.getData();

        UIFEvent eventTarget = UIFEvent.getEvent(eventName);
        if (eventTarget == null) return;

        String screenID = data.get(0);
        String compID = null;
        if (data.size() > 1) compID = data.get(1);

        UIFBaseEvent event = null;

        switch (eventTarget) {
            case UIFCompCreateEvent:
                event = new UIFCompCreateEvent(player, screenID, compID);
                break;
            case UIFCompRemoveEvent:
                event = new UIFCompRemoveEvent(player, screenID, compID);
                break;
            case UIFCompMouseEnterEvent:
                event = new UIFCompMouseEnterEvent(player, screenID, compID);
                break;
            case UIFCompMouseLeaveEvent:
                event = new UIFCompMouseLeaveEvent(player, screenID, compID);
                break;
            case UIFScreenOpenEvent:
                event = new UIFScreenOpenEvent(player, screenID);
                break;
            case UIFScreenCloseEvent:
                event = new UIFScreenCloseEvent(player, screenID);
                break;
            case UIFScreenChatOpenEvent:
                event = new UIFScreenChatOpenEvent(player, screenID);
                break;
            case UIFScreenChatCloseEvent:
                event = new UIFScreenChatCloseEvent(player, screenID);
                break;
            case UIFScreenReloadEvent:
                event = new UIFScreenReloadEvent(player, screenID);
                break;

            case UIFCompSubmitEvent:
                SubmitParams params = new SubmitParams(new LinkedList<>(data.subList(2, data.size())));
                event = new UIFCompSubmitEvent(player, screenID, compID, params);
                break;

            case UIFCompClickEvent:
                ClickType compTypeA = ClickType.valueOf(data.get(2));
                event = new UIFCompClickEvent(player, screenID, compID, compTypeA);
                break;
            case UIFCompClickReleaseEvent:
                ClickType compTypeB = ClickType.valueOf(data.get(2));
                event = new UIFCompClickReleaseEvent(player, screenID, compID, compTypeB);
                break;
            case UIFCompMouseWheelEvent:
                int valueA = Integer.parseInt(data.get(2));
                event = new UIFCompMouseWheelEvent(player, screenID, compID, valueA);
                break;
            case UIFCompTextChangeEvent:
                String text = data.get(2);
                event = new UIFCompTextChangeEvent(player, screenID, compID, text);
                break;
            case UIFScreenKeyPressEvent:
                String key = data.get(1);
                event = new UIFScreenKeyPressEvent(player, screenID, key);
                break;
            case UIFScreenMouseWheelEvent:
                int valueB = Integer.parseInt(data.get(1));
                event = new UIFScreenMouseWheelEvent(player, screenID, valueB);
                break;
            case UIFScreenReceiveMsgEvent:
                String msg = data.get(1);
                event = new UIFScreenReceiveMsgEvent(player, screenID, msg);
                break;

            case UIFScreenClickEvent:
                ClickType screenClickA = ClickType.valueOf(data.get(2));
                event = new UIFScreenClickEvent(player, screenID, compID, screenClickA);
                break;
            case UIFScreenClickReleaseEvent:
                ClickType screenClickB = ClickType.valueOf(data.get(1));
                event = new UIFScreenClickReleaseEvent(player, screenID, screenClickB);
                break;
            default:
                break;
        }

        if (event == null) return;
        event.call();
    }
}
