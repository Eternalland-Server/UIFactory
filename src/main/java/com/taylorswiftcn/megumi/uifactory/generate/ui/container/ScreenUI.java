package com.taylorswiftcn.megumi.uifactory.generate.ui.container;

import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.HudType;
import com.taylorswiftcn.megumi.uifactory.generate.type.MatchType;
import com.taylorswiftcn.megumi.uifactory.generate.type.ScreenPriority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ScreenUI extends BasicContainer{

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
        yaml.set("hideHud", hideList);

        Map<String, String> functionMap = new LinkedHashMap<>();
        functions.forEach(element -> {
            if (element.getParam() == null) {
                functionMap.put(element.getName(), String.format("func.Packet_Send('%s','%s');", element.getEvent(), getID()));
            }
            else {
                functionMap.put(element.getName(), String.format("func.Packet_Send('%s','%s', %s);", element.getEvent(), getID(), element.getParam()));
            }
        });
        yaml.set("Functions", functionMap);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "texture");
        body.put("x", String.format("(w-%f)/2", width));
        body.put("y", String.format("(h-%f)/2", height));
        body.put("width", width);
        body.put("height", height);
        body.put("texture", texture);
        yaml.set("body", body);

        getComponents().forEach((id, component) -> {
            Map<String, Object> build = component.build(player);
            yaml.set(id, build);
        });

        return yaml;
    }

    public BasicContainer setMatch(String match) {
        this.match = match;
        return this;
    }

    public BasicContainer setMatch(MatchType match) {
        this.match = match.getName();
        return this;
    }

    public BasicContainer setPriority(ScreenPriority priority) {
        this.priority = priority;
        return this;
    }

    public BasicContainer setItemAtCursorSize(Integer itemAtCursorSize) {
        this.itemAtCursorSize = Math.min(itemAtCursorSize, 50);
        return this;
    }

    public BasicContainer setInteractHUD(Boolean interactHUD) {
        this.interactHUD = interactHUD;
        return this;
    }

    public BasicContainer setAllowThrough(Boolean allowThrough) {
        this.allowThrough = allowThrough;
        return this;
    }

    public BasicContainer setAllowEsc(Boolean allowEsc) {
        this.allowEsc = allowEsc;
        return this;
    }

    public BasicContainer addHideHUD(HudType type) {
        if (!this.hideHUD.contains(type)) this.hideHUD.add(type);
        return this;
    }

    public BasicContainer addFunctions(FunctionType type) {
        if (!this.functions.contains(type)) this.functions.add(type);
        return this;
    }
}
