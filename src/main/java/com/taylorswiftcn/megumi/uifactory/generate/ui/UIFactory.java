package com.taylorswiftcn.megumi.uifactory.generate.ui;

import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI;
import eos.moe.dragoncore.network.PacketSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UIFactory {

    private static Map<UUID, ScreenUI> playerScreens = new ConcurrentHashMap<>();

    public static void open(Player player, ScreenUI screen) {
        closeScreen(player);
        playerScreens.put(player.getUniqueId(), screen);
        YamlConfiguration yaml = screen.build(player);

        PacketSender.sendYaml(player, String.format("Gui/%s.yml", screen.getID()), yaml);
        if (screen.getMatch() == null) {
            PacketSender.sendOpenGui(player, screen.getID());
        }
    }

    public static ScreenUI getOpenScreen(Player player) {
        return playerScreens.get(player.getUniqueId());
    }

    public static void closeScreen(Player player, String id) {
        ScreenUI ui = playerScreens.get(player.getUniqueId());
        if (ui != null && ui.getID().equals(id)) {
            playerScreens.remove(player.getUniqueId());
            player.closeInventory();
        }
    }

    public static void closeScreen(Player player) {
        playerScreens.remove(player.getUniqueId());
        player.closeInventory();
    }
}
