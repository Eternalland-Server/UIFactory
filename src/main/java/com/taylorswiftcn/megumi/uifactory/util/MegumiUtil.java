package com.taylorswiftcn.megumi.uifactory.util;

import com.taylorswiftcn.megumi.uifactory.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class MegumiUtil {
    private static Main plugin = Main.getInstance();

    public static void log(String s) {
        plugin.getLogger().info(s);
    }

    public static String onReplace(String text) {
        return StringUtils.replace(text, "&", "§");
    }

    public static List<String> onReplace(List<String> texts) {
        List<String> list = new ArrayList<>();
        for (String s : texts) {
            list.add(onReplace(s));
        }
        return list;
    }

    public static boolean isNumber(String a) {
        return a.matches("^[0-9]*[1-9][0-9]*$");
    }

    public static boolean isInteger(String a) {
        return a.matches("^\\d+$");
    }

    public static boolean isFloat(String a) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(a).matches();
    }

    public static void copyFile(InputStream inputStream, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] arrayOfByte = new byte['?'];
            int i;
            while ((i = inputStream.read(arrayOfByte)) > 0) {
                fileOutputStream.write(arrayOfByte, 0, i);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executionCmd(Player p, List<String> cmd) {
        for (String s : cmd) {
            String[] args = StringUtils.split(s, ":");
            String type = args[0];
            String command = StringUtils.replace(args[1], "%player%", p.getName());
            if (type.equalsIgnoreCase("op")) {
                opCmd(p, command);
            }
            if (type.equalsIgnoreCase("console")) {
                consoleCmd(command);
            }
            if (type.equalsIgnoreCase("player")) {
                playerCmd(p, command);
            }
        }
    }

    public static void opCmd(Player p, String cmd) {
        try {
            if (p.isOp()) {
                p.performCommand(cmd);
            } else {
                p.setOp(true);
                p.performCommand(cmd);
                p.setOp(false);
            }
        }
        catch (Exception ignored) {}
        finally {
            p.setOp(false);
        }
    }

    public static void consoleCmd(String cmd) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }

    public static void playerCmd(Player p, String cmd) {
        p.performCommand(cmd);
    }

    public static UUID getOfflinePlayerUUID(String name) {
        return UUID.nameUUIDFromBytes(String.format("OfflinePlayer:%s", new Object[] { name }).getBytes(StandardCharsets.UTF_8));
    }
}
