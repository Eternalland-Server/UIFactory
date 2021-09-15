package com.taylorswiftcn.megumi.uifactory.commands;

import com.taylorswiftcn.megumi.uifactory.Main;
import com.taylorswiftcn.megumi.uifactory.file.sub.ConfigFile;
import com.taylorswiftcn.megumi.uifactory.file.sub.MessageFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class MegumiCommand {
    private Main plugin;
    private boolean isPlayer;
    private Player player;

    public final void execute(CommandSender commandSender, String[] strings) {
        this.plugin = Main.getInstance();
        this.isPlayer = commandSender instanceof Player;
        if (isPlayer) player = (Player) commandSender;
        if (playerOnly() && !isPlayer) return;
        if (getPermission() != null && !commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage(ConfigFile.Prefix + MessageFile.NoPermission.replace("%s%",getPermission()));
            return;
        }
        perform(commandSender, strings);
    }

    public Main getPlugin() {
        return plugin;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public String getPermission() {
        if (getPT() == null) return null;
        return String.format("%s.%s", plugin.getName(), getPT().name()).toLowerCase();
    }

    public abstract void perform(CommandSender CommandSender, String[] Strings);

    public abstract boolean playerOnly();

    public abstract PermissionType getPT();
}