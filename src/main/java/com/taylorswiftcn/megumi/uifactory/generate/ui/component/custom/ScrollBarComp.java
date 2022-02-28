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

    private TextureComp track;
    private TextureComp thumb;
    private TextureComp extendNode;
    private LinkedList<BasicComponent> contents;
    private double thumbRollDistance;
    private double averageLineHeight;
    private double normalLineHeight;

    public ScrollBarComp(String id, double thumbRollDistance, double averageLineHeight, double normalLineHeight) {
        super(id);
        this.thumbRollDistance = thumbRollDistance;
        this.averageLineHeight = averageLineHeight;
        this.normalLineHeight = normalLineHeight;
        this.contents = new LinkedList<>();
    }

    public ScrollBarComp setTrack(TextureComp track) {
        this.track = track;
        return this;
    }

    public ScrollBarComp setThumb(TextureComp thumb) {
        this.thumb = thumb;
        this.setExtendNode();
        this.addAction(ActionType.Wheel, String.format("%s.distanceY = %s.distanceY - func.mouse_get_wheel * %s;", thumb.getID(), thumb.getID(), thumbRollDistance));
        return this;
    }


    private void setExtendNode() {
        this.extendNode = new TextureComp(getID() + "_sub");
        this.extendNode.setX(String.format("%s.x", getID()));
        this.extendNode.setY(String.format("%s.y - %s", getID(), getFollowY()));
        this.extendNode.setLimitX(getID() + ".x");
        this.extendNode.setLimitY(getID() + ".y");
        this.extendNode.setLimitWidth(getID() + ".width -" + thumb.getID() + ".width");
        this.extendNode.setLimitHeight(getID() + ".height");
    }

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
        return String.format("(%s.distanceY / %s) * %s", thumb.getID(), averageLineHeight, normalLineHeight);
    }
}
