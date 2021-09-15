package com.taylorswiftcn.megumi.uifactory.generate.ui.container;

import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType;
import com.taylorswiftcn.megumi.uifactory.generate.type.HudType;
import com.taylorswiftcn.megumi.uifactory.generate.type.MatchType;
import com.taylorswiftcn.megumi.uifactory.generate.type.ScreenPriority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ScreenUI extends BasicContainer{

    private Double width;
    private Double height;
    private String texture;

    private String match;
    private ScreenPriority priority;
    private Boolean interactHUD;
    private Boolean allowThrough;
    private Boolean allowEsc;
    private List<HudType> hideHUD;
    private List<FunctionType> functions;

    public ScreenUI(Double width, Double height, String texture) {
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    @Override
    public Map<String, Map<String, Object>> build(Player player) {
        return null;
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
