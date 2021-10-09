package com.taylorswiftcn.megumi.uifactory.listener;

import com.taylorswiftcn.megumi.uifactory.event.comp.UIFCompClickEvent;
import com.taylorswiftcn.megumi.uifactory.event.screen.UIFScreenCloseEvent;
import com.taylorswiftcn.megumi.uifactory.event.screen.UIFScreenOpenEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TestListener implements Listener {

    @EventHandler
    public void onClick(UIFCompClickEvent e) {
        Player player = e.getPlayer();
        String compID = e.getId();
        String clickType = e.getClickType().name();
        player.sendMessage(compID + ": " + clickType);
    }

    @EventHandler
    public void onOpen(UIFScreenOpenEvent e) {
        Player player = e.getPlayer();
        String screenID = e.getId();
        player.sendMessage(screenID);
    }

    @EventHandler
    public void onClose(UIFScreenCloseEvent e) {
        Player player = e.getPlayer();
        String screenID = e.getId();
        player.sendMessage(screenID);
    }
}
