package com.taylorswiftcn.megumi.uifactory.generate.ui.screen;

import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.HudType;
import com.taylorswiftcn.megumi.uifactory.generate.type.MatchType;
import com.taylorswiftcn.megumi.uifactory.generate.type.ScreenPriority;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub.SlotComp;
import eos.moe.dragoncore.network.PacketSender;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ScreenUI extends BasicScreen {

    private double width;
    private double height;
    private String texture;

    private String match;
    private ScreenPriority priority;
    private Integer itemAtCursorSize;
    private Boolean interactHUD;
    private Boolean allowThrough;
    private Boolean allowEsc;
    private List<HudType> hideHUD;
    private List<FunctionType> functions;

    public ScreenUI(String id, double width, double height, String texture) {
        super(id);
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.hideHUD = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.addFunctions(FunctionType.Open).addFunctions(FunctionType.Close);
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

        Map<String, String> functionMap = new LinkedHashMap<>();
        functions.forEach(element -> {
            if (element.getParam() == null) {
                functionMap.put(element.getName(), String.format("func.Packet_Send('%s','%s');", element.getEvent().getName(), getID()));
            }
            else {
                functionMap.put(element.getName(), String.format("func.Packet_Send('%s','%s', %s);", element.getEvent().getName(), getID(), element.getParam()));
            }
        });
        yaml.set("Functions", functionMap.size() == 0 ? null : functionMap);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "texture");
        body.put("x", String.format("(w-%f)/2", width));
        body.put("y", String.format("(h-%f)/2", height));
        body.put("width", width);
        body.put("height", height);
        body.put("texture", texture);
        yaml.set("body", body);

        getComponents().forEach((id, component) -> {
            if (component instanceof SlotComp) {
                SlotComp comp = (SlotComp) component;
                if (!comp.getIdentifier().startsWith("container_")) {
                    PacketSender.putClientSlotItem(player, comp.getIdentifier(), comp.getItem());
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
        if (!this.functions.contains(type)) this.functions.add(type);
        return this;
    }
}
