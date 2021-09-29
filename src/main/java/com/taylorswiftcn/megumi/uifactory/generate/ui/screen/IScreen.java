package com.taylorswiftcn.megumi.uifactory.generate.ui.screen;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.IComponent;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;

public interface IScreen {

    String getID();

    Map<String, IComponent> getComponents();

    IComponent getComponent(String id);

    IScreen addComponent(IComponent component);

    YamlConfiguration build(Player player);
}
