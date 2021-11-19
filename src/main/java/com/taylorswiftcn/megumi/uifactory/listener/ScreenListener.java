package com.taylorswiftcn.megumi.uifactory.listener;

import com.taylorswiftcn.megumi.uifactory.UIFactoryPlugin;
import com.taylorswiftcn.megumi.uifactory.event.screen.UIFScreenCloseEvent;
import com.taylorswiftcn.megumi.uifactory.event.screen.UIFScreenOpenEvent;
import net.sakuragame.eternal.dragoncore.api.CoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ScreenListener implements Listener {

    @EventHandler
    public void onOpen(UIFScreenOpenEvent e) {
        Player player = e.getPlayer();
        String screenID = e.getScreenID();

        Bukkit.getScheduler().runTaskLater(UIFactoryPlugin.getInstance(), () -> {
            CoreAPI.addOpenScreen(player.getUniqueId(), screenID);
        }, 1);
    }

    @EventHandler
    public void onClose(UIFScreenCloseEvent e) {
        Player player = e.getPlayer();

        CoreAPI.delOpenScreen(player.getUniqueId());
    }
}
