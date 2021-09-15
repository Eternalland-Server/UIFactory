package com.taylorswiftcn.megumi.uifactory.commands.sub;

import com.taylorswiftcn.megumi.uifactory.commands.MegumiCommand;
import com.taylorswiftcn.megumi.uifactory.commands.PermissionType;
import com.taylorswiftcn.megumi.uifactory.file.sub.MessageFile;
import org.bukkit.command.CommandSender;

public class HelpCommand extends MegumiCommand {
    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        MessageFile.Help.forEach(CommandSender::sendMessage);
        if (CommandSender.hasPermission(String.format("%s.%s", getPlugin().getName(), "admin").toLowerCase()))
            MessageFile.AdminHelp.forEach(CommandSender::sendMessage);
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public PermissionType getPT() {
        return null;
    }
}
