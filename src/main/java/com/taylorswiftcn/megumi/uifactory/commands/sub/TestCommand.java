package com.taylorswiftcn.megumi.uifactory.commands.sub;

import com.taylorswiftcn.megumi.uifactory.commands.MegumiCommand;
import com.taylorswiftcn.megumi.uifactory.commands.PermissionType;
import com.taylorswiftcn.megumi.uifactory.file.sub.ConfigFile;
import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.SubmitParams;
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
                .addFunctions(FunctionType.Open)
                .addFunctions(FunctionType.Open, "var.test = 'test'")
                .addComponent(new BodyComp("body", "255,255,255,155").setCenter().setCompSize(1222.0 / 3.0, 1121.0 / 3.0))
                .addComponent(new SlotComp("MaterialA", "MaterialA").setXY(215, 57).setScale(2))
                .addComponent(new SlotComp("MaterialB", "MaterialB").setXY(274, 57).setScale(2))
                .addComponent(new SlotComp("MaterialC", "MaterialC").setXY(215, 103).setScale(2))
                .addComponent(new SlotComp("MaterialD", "MaterialD").setXY(274, 103).setScale(2))
                .addComponent(new SlotComp("CreatureEgg", "CreatureEgg").setXY(101, 71).setScale(3.1))
                .addComponent(new SlotComp("EvolveItem", "EvolveItem").setXY(101, 166).setScale(3.1))
                .addComponent(new ButtonComp("ConfirmEvolve", "0,0,0,0", "75,75,75,155")
                        .setXY(175, 172)
                        .setCompSize(426 / 3.0, 113 / 3.0)
                        .addAction(ActionType.Left_Release, new SubmitParams().setCondition("var.test != ''").add("var.test"))
                        .addAction(ActionType.Left_Release, "func.message('Evolve success!')")
                );

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
