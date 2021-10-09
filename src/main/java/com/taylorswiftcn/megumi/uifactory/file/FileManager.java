package com.taylorswiftcn.megumi.uifactory.file;

import com.taylorswiftcn.megumi.uifactory.file.sub.ConfigFile;
import com.taylorswiftcn.megumi.uifactory.file.sub.MessageFile;
import com.taylorswiftcn.megumi.uifactory.util.MegumiUtil;
import com.taylorswiftcn.megumi.uifactory.UIFactoryPlugin;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileManager {
    private UIFactoryPlugin plugin;
    @Getter private YamlConfiguration config;
    @Getter private YamlConfiguration message;

    public FileManager(UIFactoryPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        config = initFile("config.yml");
        message = initFile("message.yml");

        ConfigFile.init();
        MessageFile.init();
    }

    private YamlConfiguration initFile(String name) {
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            MegumiUtil.copyFile(plugin.getResource(name), file);
            MegumiUtil.log(String.format("File: 已生成 %s 文件", name));
        }
        else MegumiUtil.log(String.format("File: 已加载 %s 文件", name));
        return YamlConfiguration.loadConfiguration(file);
    }
}
