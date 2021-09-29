package com.taylorswiftcn.megumi.uifactory.commands.sub;

import com.taylorswiftcn.megumi.uifactory.commands.MegumiCommand;
import com.taylorswiftcn.megumi.uifactory.commands.PermissionType;
import com.taylorswiftcn.megumi.uifactory.file.sub.ConfigFile;
import com.taylorswiftcn.megumi.uifactory.generate.ui.UIFactory;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.ButtonComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.SlotComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.custom.BodyComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class TestCommand extends MegumiCommand {
    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        Player player = getPlayer();

        ScreenUI ui = new ScreenUI("test")
                .setAllowEsc(true)
                .setPressEKeyClose()
                .generateContainerSlot(69.5, 240.5, 1.3, 10, 10)
                .addComponent(new BodyComp("body", "mythicalcreature/background.png").setCenter().setCompSize(1222.0 / 3.0, 1121.0 / 3.0))
                .addComponent(new ButtonComp("ConfirmEvolve", "0,0,0,0", "mythicalcreature/after_click.png").setXY(175, 172).setCompSize(426 / 3.0, 113 / 3.0))
                .addComponent(new SlotComp("MaterialA", "材料槽位1").setXY(215, 57).setScale(2))
                .addComponent(new SlotComp("MaterialB", "材料槽位2").setXY(274, 57).setScale(2))
                .addComponent(new SlotComp("MaterialC", "材料槽位3").setXY(215, 103).setScale(2))
                .addComponent(new SlotComp("MaterialD", "材料槽位4").setXY(274, 103).setScale(2))
                .addComponent(new SlotComp("CreatureEgg", "神兽蛋槽位").setXY(101, 71).setScale(3.1))
                .addComponent(new SlotComp("EvolveItem", "进阶品槽位").setXY(101, 166).setScale(3.1));

        File file = new File(getPlugin().getDataFolder(), "test.yml");
        YamlConfiguration yaml = ui.build(player);
        try {
            yaml.save(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        UIFactory.open(player, ui);
        player.sendMessage(ConfigFile.Prefix + "done!");
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public PermissionType getPT() {
        return PermissionType.Admin;
    }
}
