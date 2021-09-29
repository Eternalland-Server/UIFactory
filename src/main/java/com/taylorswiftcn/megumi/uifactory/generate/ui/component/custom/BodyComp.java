package com.taylorswiftcn.megumi.uifactory.generate.ui.component.custom;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.ImageComp;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class BodyComp extends ImageComp {

    public BodyComp(String id, String texture) {
        super(id, texture);
    }

    public BodyComp setHCenter() {
        this.setX(String.format("(w-%s.width)/2", getID()));
        return this;
    }

    public BodyComp setVCenter() {
        this.setY(String.format("(h-%s.height)/2", getID()));
        return this;
    }

    public BodyComp setCenter() {
        this.setX(String.format("(w-%s.width)/2", getID()));
        this.setY(String.format("(h-%s.height)/2", getID()));
        return this;
    }
}
