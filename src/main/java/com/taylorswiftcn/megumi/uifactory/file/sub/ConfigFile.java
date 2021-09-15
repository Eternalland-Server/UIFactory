package com.taylorswiftcn.megumi.uifactory.file.sub;

import com.taylorswiftcn.megumi.uifactory.Main;
import com.taylorswiftcn.megumi.uifactory.util.MegumiUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class ConfigFile {
    private static YamlConfiguration config;

    public static String Prefix;

    public static void init() {
        config = Main.getInstance().getFileManager().getConfig();

        Prefix = getString("Prefix");
    }

    private static String getString(String path) {
        return MegumiUtil.onReplace(config.getString(path));
    }

    private static List<String> getStringList(String path) {
        return MegumiUtil.onReplace(config.getStringList(path));
    }
}
