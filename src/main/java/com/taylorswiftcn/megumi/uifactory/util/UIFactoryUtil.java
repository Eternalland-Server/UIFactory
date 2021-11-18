package com.taylorswiftcn.megumi.uifactory.util;

import com.taylorswiftcn.megumi.uifactory.UIFactoryPlugin;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class UIFactoryUtil {

    private final static UIFactoryPlugin plugin = UIFactoryPlugin.getInstance();

    public static String onReplace(String text) {
        return StringUtils.replace(text, "&", "§");
    }

    public static String getPlaySoundFunc(Sound sound) {
        return "func.Sound_Play('" + sound.name().replace("_", ".") + "');";
    }
}
