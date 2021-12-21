package com.taylorswiftcn.megumi.uifactory.commands.sub;

import com.taylorswiftcn.megumi.uifactory.commands.SubCommand;
import com.taylorswiftcn.megumi.uifactory.commands.CommandPerms;
import com.taylorswiftcn.megumi.uifactory.generate.function.Statements;
import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType;
import com.taylorswiftcn.megumi.uifactory.generate.function.SubmitParams;
import com.taylorswiftcn.megumi.uifactory.generate.ui.UIFactory;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.ButtonComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.SlotComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.custom.BodyComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.custom.ScrollBarComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExampleCommand extends SubCommand {
    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        Player player = getPlayer();

        // 滚轮栏组件
        ScrollBarComp comp = new ScrollBarComp("scrollbar", 27, 27);
        comp
                .setTexture("255,255,255,100")
                .setXY("0", "0")
                .setCompSize(200, 240);
        comp
                .setTrack((TextureComp) new TextureComp("scrollbar_track")
                        .setTexture("0,0,0,200")
                        .setXY(0, 0)
                        .setCompSize(5, 240)
                )
                // 设置滚动栏
                .setThumb((TextureComp) new TextureComp("scrollbar_thumb")
                        .setTexture("255,0,0,100")
                        .setXY( "%body%.x + %body%.width - %bar%.width", "%body%.y")
                        .setCompSize(5, 10)
                )
                // 添加滚动栏内组件
                .addContent(new TextureComp("test1")
                        .setTexture("255,255,0,100")
                        .setXY("scrollbar.x + 3", "scrollbar.y + 3 - " + comp.getFollowY())
                        .setCompSize(24, 24)
                )
                .addContent(new TextureComp("test2")
                        .setTexture("255,255,0,100")
                        .setXY("test1.x", "test1.y + test1.height + 3")
                        .setCompSize(24, 24)
                )
                .addContent(new TextureComp("test3")
                        .setTexture("255,255,0,100")
                        .setXY("test1.x", "test2.y + test2.height + 3")
                        .setCompSize(24, 24)
                )
                .addContent(new TextureComp("test3_1")
                        .setTexture("255,255,0,100")
                        .setXY("test3.x + test3.width + 3", "test3.y")
                        .setCompSize(24, 24)
                )
                .addContent(new TextureComp("test4")
                        .setTexture("255,255,0,100")
                        .setXY("test1.x", "test3.y + test3.height + 3")
                        .setCompSize(24, 24)
                )
                .addContent(new TextureComp("test5")
                        .setTexture("255,255,0,100")
                        .setXY("test1.x", "test4.y + test4.height + 3")
                        .setCompSize(24, 24)
                );

        ScreenUI ui = new ScreenUI("test")
                .setAllowEsc(true)
                .setPressEKeyClose()
                .addFunctions(FunctionType.Open)
                .addFunctions(FunctionType.Open, "var.test = 'test'")
                .addComponent(new BodyComp("body", "255,200,255,155").setCenter().setCompSize(1222.0 / 3.0, 1121.0 / 3.0))
                .addComponent(new SlotComp("MaterialA", "MaterialA").setXY(215, 57).setScale(2))
                .addComponent(new SlotComp("MaterialB", "MaterialB").setXY(274, 57).setScale(2))
                .addComponent(new SlotComp("MaterialC", "MaterialC").setXY(215, 103).setScale(2))
                .addComponent(new SlotComp("MaterialD", "MaterialD").setXY(274, 103).setScale(2))
                .addComponent(new SlotComp("CreatureEgg", "CreatureEgg").setXY(101, 71).setScale(3.1))
                .addComponent(new SlotComp("EvolveItem", "EvolveItem").setXY(101, 166).setScale(3.1))
                .addComponent(new ButtonComp("ConfirmEvolve", "0,0,0,0", "255,75,75,155")
                        .setXY(175, 172)
                        .setCompSize(426 / 3.0, 113 / 3.0)
                        .addAction(ActionType.Left_Release,
                                new SubmitParams()
                                        .setCondition("var.test != ''")
                                        .add("var.test")
                        )
                        .addAction(ActionType.Left_Release, "func.message('Evolve success!')")
                        .addAction(ActionType.Enter,
                                new Statements()
                                        .add("func.message('You sure you want an advanced equip?');")
                                        .add("func.message('You sure you want an advanced equip?');")
                                        .add("func.message('You sure you want an advanced equip?');")
                                        .build()
                        )
                )
                .generateContainerSlot("75,75,75,155", 69.5, 240.5, 1.3, 1, 1, 10, 10, 12d)
                // 将滚轮组件放入UI
                .addComponent(comp);


        /*File file = new File(DragonCore.getInstance().getDataFolder(), "test.yml");
        YamlConfiguration yaml = ui.build(player);
        try {
            yaml.save(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/

        UIFactory.open(player, ui);
        player.sendMessage("§7§l[UIFactory] §adone!");
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
