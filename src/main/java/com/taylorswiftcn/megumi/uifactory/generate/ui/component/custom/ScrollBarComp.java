package com.taylorswiftcn.megumi.uifactory.generate.ui.component.custom;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class ScrollBarComp extends TextureComp {

    private TextureComp thumb;
    private TextureComp region;
    private TextureComp extendNode;
    private LinkedList<BasicComponent> contents;
    private double trDistance;
    private double rrDistance;

    public ScrollBarComp(String id, double thumbRollDistance, double regionRollDistance) {
        super(id);
        this.trDistance = thumbRollDistance;
        this.rrDistance = regionRollDistance;
        this.contents = new LinkedList<>();
        this.addAction(ActionType.Wheel, String.format("%s.distanceY = %s.distanceY - func.mouse_get_wheel * %s;", thumb.getID(), thumb.getID(), trDistance));
    }

    public ScrollBarComp setThumb(TextureComp thumb) {
        this.thumb = thumb;
        this.thumb.setMaxMoveY(String.format("%s.height - %s.height", getID(), thumb.getID()));
        this.setScrollRegion();
        this.setExtendNode();
        return this;
    }

    private void setScrollRegion() {
        this.region = new TextureComp(getID() + "_region");
        this.region.setX(String.format("%s.x", getID()));
        this.region.setY(String.format("%s.y - %s", getID(), getFollowY()));
    }

    private void setExtendNode() {
        this.extendNode = new TextureComp(getID() + "_sub");
        this.extendNode.setLimitX(getID() + ".x");
        this.extendNode.setLimitY(getID() + ".y");
        this.extendNode.setLimitWidth(getID() + ".width -" + thumb.getID() + ".width");
        this.extendNode.setLimitHeight(getID() + ".height");
    }

    /*public ScrollBarComp setThumb(String texture, String x, String y, String width, String height) {
        this.thumb = new TextureComp(getID() + "_bar");
        this.thumb.setTexture(texture);
        this.thumb.setXY(
                x.replace("%body%", getID()).replace("%bar%", thumb.getID()),
                y.replace("%body%", getID()).replace("%bar%", thumb.getID())
        );
        this.thumb.setCompSize(width, height);
        this.thumb.setMaxMoveY(getID() + ".height-" + thumb.getID() + ".height");

        return this;
    }*/

    public ScrollBarComp addContent(BasicComponent component) {
        component.setExtend(extendNode.getID());
        contents.add(component);
        return this;
    }

    public ScrollBarComp addContent(LinkedList<BasicComponent> components) {
        components.forEach(c -> {
            c.setExtend(extendNode.getID());
            contents.add(c);
        });
        return this;
    }

    public String getFollowY() {
        return String.format("func.ceil(%s.distanceY / %s) * %s", thumb.getID(), trDistance, rrDistance);
    }
}
