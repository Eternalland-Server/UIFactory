package com.taylorswiftcn.megumi.uifactory.generate.ui.container;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.IComponent;

import java.util.Map;

public abstract class BasicContainer implements IContainer {

    private String id;
    private Map<String, IComponent> components;

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
    public void addComponent(IComponent component) {
        components.put(component.getID(), component);
    }
}
