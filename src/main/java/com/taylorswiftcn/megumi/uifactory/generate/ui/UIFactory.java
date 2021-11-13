package com.taylorswiftcn.megumi.uifactory.generate.ui;

import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI;
import net.sakuragame.eternal.dragoncore.config.FolderType;
import net.sakuragame.eternal.dragoncore.network.PacketSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UIFactory {

    private final static Map<UUID, ScreenUI> playerScreens = new ConcurrentHashMap<>();

    public static void open(Player player, ScreenUI screen) {
        YamlConfiguration yaml = screen.build(player);
        PacketSender.sendYaml(player, FolderType.Gui, screen.getID(), yaml);

        if (screen.getMatch() == null) {
            closeScreen(player);
            playerScreens.put(player.getUniqueId(), screen);
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
