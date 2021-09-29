package com.taylorswiftcn.megumi.uifactory.generate.ui.component.base;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class ButtonComp extends ImageComp {

    private String textureClicked;

    public ButtonComp(String id, String texture, String textureClicked) {
        this(id, texture, textureClicked, null, null, null);
    }

    public ButtonComp(String id, String texture, String textureClicked, String textureHovered, String text, String textColor) {
        super(id, texture, textureHovered, text, textColor);
        this.textureClicked = textureClicked;
        this
                .addAction(ActionType.Left_Click)
                .addAction(ActionType.Left_Release);

        if (textureClicked == null) return;
        this
                .addAction(ActionType.Left_Click, String.format("func.component_set('%s', 'texture', '%s');", getID(), textureClicked))
                .addAction(ActionType.Left_Release, String.format("func.component_set('%s', 'texture', '%s');", getID(), getTexture()));
    }

    public ButtonComp setTextureClicked(String textureClicked) {
        this.textureClicked = textureClicked;
        return this;
    }
}
