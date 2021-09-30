package com.taylorswiftcn.megumi.uifactory.generate.ui.component;

import java.util.Map;

public interface IComponent {

    String getID();

    Map<String, Object> build();
}
