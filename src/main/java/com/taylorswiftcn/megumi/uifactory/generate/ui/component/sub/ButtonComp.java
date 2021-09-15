package com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class ButtonComp extends ImageComp {

    public ButtonComp(String id, String texture) {
        super(id, texture);
    }

    public ButtonComp(String id, String texture, String textureHovered) {
        super(id, texture, textureHovered);
    }

    public ButtonComp(String id, String texture, String textureHovered, String text) {
        super(id, texture, textureHovered, text);
    }

    public ButtonComp(String id, String texture, String textureHovered, String text, String textColor) {
        super(id, texture, textureHovered, text, textColor);
        this.addAction(ActionType.Left_Click);
        this.addAction(ActionType.Right_Click);
    }
}
