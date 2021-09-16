package com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class ButtonComp extends ImageComp {

    public ButtonComp(String id, String texture) {
        this(id, texture, null, null, null);
    }

    public ButtonComp(String id, String texture, String textureHovered) {
        this(id, texture, textureHovered, null, null);
    }

    public ButtonComp(String id, String texture, String textureHovered, String text) {
        this(id, texture, textureHovered, text, null);
    }

    public ButtonComp(String id, String texture, String textureHovered, String text, String textColor) {
        super(id, texture, textureHovered, text, textColor);
        this.addAction(ActionType.Left_Click);
        this.addAction(ActionType.Right_Click);
    }
}
