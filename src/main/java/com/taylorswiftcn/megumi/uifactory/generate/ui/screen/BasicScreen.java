package com.taylorswiftcn.megumi.uifactory.generate.ui.screen;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.IComponent;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BasicScreen implements IScreen {

    private String id;
    private Map<String, IComponent> components;

    public BasicScreen(String id) {
        this.id = id;
        this.components = new LinkedHashMap<>();
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Map<String, IComponent> getComponents() {
        return components;
    }

    @Override
    public IComponent getComponent(String id) {
        return components.get(id);
    }

    @Override
    public BasicScreen addComponent(IComponent component) {
        components.put(component.getID(), component);
        return this;
    }
}
