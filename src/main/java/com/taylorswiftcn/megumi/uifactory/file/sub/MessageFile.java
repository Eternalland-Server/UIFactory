package com.taylorswiftcn.megumi.uifactory.file.sub;

import com.taylorswiftcn.megumi.uifactory.Main;
import com.taylorswiftcn.megumi.uifactory.util.MegumiUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class MessageFile {
    private static YamlConfiguration message;

    public static List<String> Help;
    public static List<String> AdminHelp;

    public static String NoPermission;

    public static void init() {
        message = Main.getInstance().getFileManager().getMessage();

        Help = getStringList("Help");
        AdminHelp = getStringList("AdminHelp");

        NoPermission = getString("Message.NoPermission");
    }

    private static String getString(String path) {
        return MegumiUtil.onReplace(message.getString(path));
    }

    private static List<String> getStringList(String path) {
        return MegumiUtil.onReplace(message.getStringList(path));
    }


}
