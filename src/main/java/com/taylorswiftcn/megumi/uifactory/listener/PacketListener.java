package com.taylorswiftcn.megumi.uifactory.listener;

import com.taylorswiftcn.megumi.uifactory.event.UIFBaseEvent;
import com.taylorswiftcn.megumi.uifactory.event.UIFEvent;
import com.taylorswiftcn.megumi.uifactory.event.comp.*;
import com.taylorswiftcn.megumi.uifactory.event.screen.*;
import com.taylorswiftcn.megumi.uifactory.generate.type.ClickType;
import com.taylorswiftcn.megumi.uifactory.generate.function.SubmitParams;
import eos.moe.dragoncore.api.gui.event.CustomPacketEvent;
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

        String id = data.get(0);

        UIFBaseEvent event = null;

        switch (eventTarget) {
            case UIFCompCreateEvent:
                event = new UIFCompCreateEvent(player, id);
                break;
            case UIFCompRemoveEvent:
                event = new UIFCompRemoveEvent(player, id);
                break;
            case UIFCompPreRenderEvent:
                event = new UIFCompPreRenderEvent(player, id);
                break;
            case UIFCompPostRenderEvent:
                event = new UIFCompPostRenderEvent(player, id);
                break;
            case UIFCompMouseEnterEvent:
                event = new UIFCompMouseEnterEvent(player, id);
                break;
            case UIFCompMouseLeaveEvent:
                event = new UIFCompMouseLeaveEvent(player, id);
                break;
            case UIFScreenOpenEvent:
                event = new UIFScreenOpenEvent(player, id);
                break;
            case UIFScreenCloseEvent:
                event = new UIFScreenCloseEvent(player, id);
                break;
            case UIFScreenChatOpenEvent:
                event = new UIFScreenChatOpenEvent(player, id);
                break;
            case UIFScreenChatCloseEvent:
                event = new UIFScreenChatCloseEvent(player, id);
                break;
            case UIFScreenReloadEvent:
                event = new UIFScreenReloadEvent(player, id);
                break;
            case UIFCompSubmitEvent:
                SubmitParams params = new SubmitParams(new LinkedList<>(data.subList(1, data.size())));
                event = new UIFCompSubmitEvent(player, id, params);
                break;

            case UIFCompClickEvent:
                ClickType typeA = ClickType.valueOf(data.get(1));
                event = new UIFCompClickEvent(player, id, typeA);
                break;
            case UIFCompClickReleaseEvent:
                ClickType typeB = ClickType.valueOf(data.get(1));
                event = new UIFCompClickReleaseEvent(player, id, typeB);
                break;
            case UIFCompMouseWheelEvent:
                int valueA = Integer.parseInt(data.get(1));
                event = new UIFCompMouseWheelEvent(player, id, valueA);
                break;
            case UIFCompTextChangeEvent:
                String text = data.get(1);
                event = new UIFCompTextChangeEvent(player, id, text);
                break;
            case UIFScreenKeyPressEvent:
                String key = data.get(1);
                event = new UIFScreenKeyPressEvent(player, id, key);
                break;
            case UIFScreenMouseWheelEvent:
                int valueB = Integer.parseInt(data.get(1));
                event = new UIFScreenMouseWheelEvent(player, id, valueB);
                break;
            case UIFScreenReceiveMsgEvent:
                String msg = data.get(1);
                event = new UIFScreenReceiveMsgEvent(player, id, msg);
            default:
                break;
        }

        if (event == null) return;
        event.call();
    }
}
