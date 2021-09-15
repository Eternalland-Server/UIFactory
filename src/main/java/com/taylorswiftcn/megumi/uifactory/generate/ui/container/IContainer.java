package com.taylorswiftcn.megumi.uifactory.generate.ui.container;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.IComponent;
import org.bukkit.entity.Player;

import java.util.Map;

public interface IContainer {

    String getID();

    Map<String, IComponent> getComponents();

    IComponent getComponent(String id);

    void addComponent(IComponent component);

    Map<String, Map<String, Object>> build(Player player);
}
