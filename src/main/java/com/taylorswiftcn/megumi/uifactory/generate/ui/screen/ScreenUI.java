package com.taylorswiftcn.megumi.uifactory.generate.ui.screen;

import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.HudType;
import com.taylorswiftcn.megumi.uifactory.generate.type.MatchType;
import com.taylorswiftcn.megumi.uifactory.generate.type.ScreenPriority;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.SlotComp;
import eos.moe.dragoncore.api.SlotAPI;
import eos.moe.dragoncore.network.PacketSender;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter
public class ScreenUI extends BasicScreen {

    private String match;
    private ScreenPriority priority;
    private Integer itemAtCursorSize;
    private Boolean interactHUD;
    private Boolean allowThrough;
    private Boolean allowEsc;
    private List<HudType> hideHUD;
    private HashMap<String, String> functions;

    public ScreenUI(String id) {
        super(id);
        this.hideHUD = new ArrayList<>();
        this.functions = new HashMap<>();
        this.addFunctions(FunctionType.Open).addFunctions(FunctionType.Close);
    }

    public ScreenUI generateContainerSlot(double x, double y, double scale, double lineSpace, double columnSpace) {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                double slotX = x + (16 * scale + lineSpace) * i;
                double slotY = y + (16 * scale + columnSpace) * j;
                BasicComponent container = SlotComp.getOriginSlot((j + 1) * 9 + i);
                if (container == null) continue;
                this.addComponent(container.setXY(slotX, slotY).setScale(scale));
            }
        }

        return this;
    }

    public ScreenUI setPressEKeyClose() {
        addFunctions(FunctionType.KeyPress, "func.Key_Get_Press == 'E' ? { func.Screen_Close } : {};");
        return this;
    }

    @Override
    public YamlConfiguration build(Player player) {
        YamlConfiguration yaml = new YamlConfiguration();
        yaml.set("match", match);
        yaml.set("priority", priority == null ? null : priority.getIndex());
        yaml.set("currentItemSize", itemAtCursorSize);
        yaml.set("interactHud", interactHUD);
        yaml.set("through", allowThrough);
        yaml.set("allowEscClose", allowEsc);

        List<String> hideList = new ArrayList<>();
        hideHUD.forEach(element -> hideList.add(element.getName()));
        yaml.set("hideHud", hideList.size() == 0 ? null : hideList);

        yaml.set("Functions", functions.size() == 0 ? null : functions);

        getComponents().forEach((id, component) -> {
            if (component instanceof SlotComp) {
                SlotComp comp = (SlotComp) component;
                if (!comp.getIdentifier().startsWith("container_")) {
                    ItemStack item = SlotAPI.getCacheSlotItem(player, comp.getIdentifier());
                    PacketSender.putClientSlotItem(player, comp.getIdentifier(), item);
                }
            }

            Map<String, Object> build = component.build();
            yaml.set(id, build);
        });

        return yaml;
    }

    public ScreenUI setMatch(String match) {
        this.match = match;
        return this;
    }

    public ScreenUI setMatch(MatchType match) {
        this.match = match.getName();
        return this;
    }

    public ScreenUI setPriority(ScreenPriority priority) {
        this.priority = priority;
        return this;
    }

    public ScreenUI setItemAtCursorSize(Integer itemAtCursorSize) {
        this.itemAtCursorSize = Math.min(itemAtCursorSize, 50);
        return this;
    }

    public ScreenUI setInteractHUD(Boolean interactHUD) {
        this.interactHUD = interactHUD;
        return this;
    }

    public ScreenUI setAllowThrough(Boolean allowThrough) {
        this.allowThrough = allowThrough;
        return this;
    }

    public ScreenUI setAllowEsc(Boolean allowEsc) {
        this.allowEsc = allowEsc;
        return this;
    }

    public ScreenUI addHideHUD(HudType type) {
        if (!this.hideHUD.contains(type)) this.hideHUD.add(type);
        return this;
    }

    public ScreenUI addFunctions(FunctionType type) {
        String eventStatement = type.getParam() == null ?
                String.format("func.Packet_Send('%s','%s');", type.getEvent().getName(), getID()) :
                String.format("func.Packet_Send('%s','%s', %s);", type.getEvent().getName(), getID(), type.getParam());

        return addFunctions(type, eventStatement);
    }

    public ScreenUI addFunctions(FunctionType type, String statement) {
        return addFunctions(type.getName(), statement);
    }

    public ScreenUI addFunctions(String funcName, String statement) {
        String current = functions.get(funcName);
        functions.merge(funcName, statement, (a, b) -> a + "\n" + b);
        return this;
    }
}
