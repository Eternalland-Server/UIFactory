package com.taylorswiftcn.megumi.uifactory.listener;

import com.taylorswiftcn.megumi.uifactory.event.screen.UIFScreenOpenEvent;
import net.sakuragame.eternal.dragoncore.api.CoreAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ScreenListener implements Listener {

    @EventHandler
    public void onOpen(UIFScreenOpenEvent e) {
        Player player = e.getPlayer();
        String screenID = e.getScreenID();

        CoreAPI.addOpenScreen(player.getUniqueId(), screenID);
    }
}
