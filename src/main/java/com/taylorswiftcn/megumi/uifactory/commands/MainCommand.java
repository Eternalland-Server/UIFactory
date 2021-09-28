package com.taylorswiftcn.megumi.uifactory.commands;

import com.taylorswiftcn.megumi.uifactory.commands.sub.HelpCommand;
import com.taylorswiftcn.megumi.uifactory.commands.sub.ReloadCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;
import java.util.stream.Collectors;

public class MainCommand implements TabExecutor {
    private HelpCommand help;
    private HashMap<String, MegumiCommand> commands;

    public MainCommand() {
        this.help = new HelpCommand();
        this.commands = new HashMap<>();
        /*this.commands.put("test", new TestCommand());*/
        this.commands.put("reload", new ReloadCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        MegumiCommand cmd = help;
        if (strings.length >= 1 && commands.containsKey(strings[0])) {
            cmd = commands.get(strings[0]);
        }
        cmd.execute(commandSender, strings);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) return null;

        List<String> keys = new ArrayList<>(commands.keySet());
        if (args.length == 0) return keys;

        return keys.stream().filter(s -> StringUtils.startsWithIgnoreCase(s, args[0])).collect(Collectors.toList());
    }
}
