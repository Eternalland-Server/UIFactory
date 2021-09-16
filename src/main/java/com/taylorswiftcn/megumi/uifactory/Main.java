package com.taylorswiftcn.megumi.uifactory;

import com.taylorswiftcn.megumi.uifactory.commands.MainCommand;
import com.taylorswiftcn.megumi.uifactory.file.FileManager;
import com.taylorswiftcn.megumi.uifactory.listener.PacketListener;
import com.taylorswiftcn.megumi.uifactory.listener.TestListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Getter private static Main instance;

    @Getter private FileManager fileManager;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();

        instance = this;

        fileManager = new FileManager(this);
        fileManager.init();

        Bukkit.getPluginManager().registerEvents(new PacketListener(), this);
        /*Bukkit.getPluginManager().registerEvents(new TestListener(), this);*/
        getCommand("uif").setExecutor(new MainCommand());

        long end = System.currentTimeMillis();

        getLogger().info("加载成功! 用时 %time% ms".replace("%time%", String.valueOf(end - start)));
    }

    @Override
    public void onDisable() {
        getLogger().info("卸载成功!");
    }

    public String getVersion() {
        String packet = Bukkit.getServer().getClass().getPackage().getName();
        return packet.substring(packet.lastIndexOf('.') + 1);
    }
}
