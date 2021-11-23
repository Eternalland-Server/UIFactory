package com.taylorswiftcn.megumi.uifactory.generate.ui.component.custom;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class ScrollBarComp extends TextureComp {

    private TextureComp bar;
    private TextureComp extendNode;

    public ScrollBarComp(String id, double rollDistance) {
        super(id);
        this.bar = new TextureComp(id + "_bar");
        this.extendNode = new TextureComp(id + "_sub");
        this.extendNode.setLimitX(id + ".x");
        this.extendNode.setLimitY(id + ".y");
        this.extendNode.setLimitWidth(id + ".width -" + bar.getID() + ".width");
        this.extendNode.setLimitHeight(id + ".height");
        this.addAction(ActionType.Wheel, bar.getID() + ".distanceY=" + bar.getID() + ".distanceY-func.mouse_get_wheel*" + rollDistance);
    }

    public ScrollBarComp setBar(String texture, String x, String y, String width, String height) {
        this.bar.setTexture(texture);
        this.bar.setXY(
                x.replace("%body%", getID()).replace("%bar%", bar.getID()),
                y.replace("%body%", getID()).replace("%bar%", bar.getID())
        );
        this.bar.setCompSize(width, height);
        this.bar.setMaxMoveY(getID() + ".height-" + bar.getID() + ".height");

        return this;
    }

    public String getExtendNodeID() {
        return extendNode.getID();
    }

    public String getSubLocationY() {
        return bar.getID() + ".dy * " + getID() + ".height";
    }
}
