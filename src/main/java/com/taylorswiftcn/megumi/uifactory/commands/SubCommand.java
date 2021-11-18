package com.taylorswiftcn.megumi.uifactory.commands;

import com.taylorswiftcn.megumi.uifactory.UIFactoryPlugin;
import com.taylorswiftcn.megumi.uifactory.util.UIFactoryUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {
    private UIFactoryPlugin plugin;
    private boolean isPlayer;
    private Player player;

    public final void execute(CommandSender commandSender, String[] strings) {
        this.plugin = UIFactoryPlugin.getInstance();
        this.isPlayer = commandSender instanceof Player;
        if (isPlayer) player = (Player) commandSender;
        if (playerOnly() && !isPlayer) {
            commandSender.sendMessage(UIFactoryUtil.onReplace(" &7控制台无法使用该命令"));
            return;
        }
        if (getPermission() != null && !commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage(UIFactoryUtil.onReplace(" &7你没有权限: &a" + getPermission()));
            return;
        }
        perform(commandSender, strings);
    }

    public UIFactoryPlugin getPlugin() {
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

    public abstract CommandPerms getPT();
}