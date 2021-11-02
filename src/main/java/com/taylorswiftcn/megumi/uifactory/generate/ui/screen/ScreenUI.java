package com.taylorswiftcn.megumi.uifactory.generate.ui.screen;

import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.HudType;
import com.taylorswiftcn.megumi.uifactory.generate.type.MatchType;
import com.taylorswiftcn.megumi.uifactory.generate.type.ScreenPriority;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.IComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.SlotComp;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp;
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
    private final List<HudType> hideHUD;
    private final List<String> imports;
    private final HashMap<String, String> functions;

    public ScreenUI(String id) {
        super(id);
        this.hideHUD = new ArrayList<>();
        this.imports = new ArrayList<>();
        this.functions = new HashMap<>();
        this.addFunctions(FunctionType.Open, "var.screenID = '" + id + "';");
    }

    /**
     * 生成背包槽
     *
     * @param slotTexture     槽位背景图片
     * @param x               x起点
     * @param y               y起点
     * @param scale           尺寸
     * @param slotOffsetX     slot组件x偏移值
     * @param slotOffsetY     slot组件y偏移值
     * @param lineSpace       行间距
     * @param columnSpace     列间距
     * @param lastColumnSpace 最后两列间距
     * @return {@link ScreenUI}
     */
    public ScreenUI generateContainerSlot(String slotTexture, double x, double y, double scale, double slotOffsetX, double slotOffsetY, double lineSpace, double columnSpace, Double lastColumnSpace) {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                double slotX = x + (16 * scale + lineSpace) * i;
                double slotY = y + (16 * scale + ((j == 3 && lastColumnSpace != null) ? lastColumnSpace : columnSpace)) * j;
                int index = (j + 1) * 9 + i;
                BasicComponent container = SlotComp.getOriginSlot(index);
                if (container == null) continue;
                this
                        .addComponent(new TextureComp("slot_bg_" + index)
                                .setTexture(slotTexture)
                                .setXY(slotX, slotY).setCompSize(18 * scale, 18 * scale)
                        )
                        .addComponent(container.setXY(slotX + slotOffsetX, slotY + slotOffsetY).setScale(scale));
            }
        }

        return this;
    }

    /**
     * 生成背包槽
     *
     * @param slotTexture     槽位背景图片
     * @param x               x起点
     * @param y               y起点
     * @param scale           尺寸
     * @param slotOffsetX     slot组件x偏移值
     * @param slotOffsetY     slot组件y偏移值
     * @param lineSpace       行间距
     * @param columnSpace     列间距
     * @param lastColumnSpace 最后两列间距
     * @return {@link ScreenUI}
     */
    public ScreenUI generateContainerSlot(String slotTexture, String x, String y, double scale, double slotOffsetX, double slotOffsetY, double lineSpace, double columnSpace, Double lastColumnSpace) {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                double slotX = (16 * scale + lineSpace) * i;
                double slotY = (16 * scale + ((j == 3 && lastColumnSpace != null) ? lastColumnSpace : columnSpace)) * j;
                int index = (j + 1) * 9 + i;
                BasicComponent container = SlotComp.getOriginSlot(index);
                if (container == null) continue;
                this
                        .addComponent(new TextureComp("slot_bg_" + index)
                                .setTexture(slotTexture)
                                .setXY(slotX, slotY).setCompSize(18 * scale, 18 * scale)
                        )
                        .addComponent(container.setXY(String.format("%s+%s+%s", x, slotX, slotOffsetX), String.format("%s+%s+%s", y, slotY, slotOffsetY)).setScale(scale));
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
        yaml.set("hideHud", hideList.isEmpty() ? null : hideList);

        yaml.set("import", imports.isEmpty() ? null : imports);
        yaml.set("Functions", functions.isEmpty() ? null : functions);

        getComponents().forEach((id, component) -> {
            if (component instanceof SlotComp) {
                SlotComp comp = (SlotComp) component;
                if (!comp.getIdentifier().startsWith("container_") && player != null) {
                    ItemStack item = SlotAPI.getCacheSlotItem(player, comp.getIdentifier());
                    PacketSender.putClientSlotItem(player, comp.getIdentifier(), item);
                }
            }

            Map<String, Object> build = component.build();
            yaml.set(id, build);
        });

        return yaml;
    }

    @Override
    public ScreenUI addComponent(IComponent component) {
        super.addComponent(component);
        return this;
    }

    @Override
    public IScreen addComponents(List<IComponent> components) {
        super.addComponents(components);
        return this;
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

    public ScreenUI addImports(String screenID) {
        imports.add(screenID);
        return this;
    }

    public ScreenUI addImports(List<String> screens) {
        imports.addAll(screens);
        return this;
    }

    public ScreenUI addFunctions(FunctionType type) {
        String eventStatement = type.getParam() == null ?
                String.format("func.Packet_Send('%s', var.screenID);", type.getEvent().getName()) :
                String.format("func.Packet_Send('%s', var.screenID, %s);", type.getEvent().getName(), type.getParam());

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

    public ScreenUI clearFunctions() {
        functions.clear();
        return this;
    }
}
