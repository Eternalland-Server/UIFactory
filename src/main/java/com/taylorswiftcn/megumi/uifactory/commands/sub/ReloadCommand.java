package com.taylorswiftcn.megumi.uifactory.commands.sub;

import com.taylorswiftcn.megumi.uifactory.commands.SubCommand;
import com.taylorswiftcn.megumi.uifactory.commands.CommandPerms;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends SubCommand {
    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {

    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public CommandPerms getPT() {
        return CommandPerms.Admin;
    }
}
