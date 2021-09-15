package com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub;

import com.taylorswiftcn.megumi.uifactory.generate.type.EntityViewType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "entity")
public class EntityViewComp extends BasicComponent {

    @ComponentField(define = "entity")
    private String entity;

    @ComponentField(define = "head")
    private Boolean showHead;

    @ComponentField(define = "followMouse")
    private Boolean followMouse;

    public EntityViewComp(String id, EntityViewType type) {
        this(id, type.getName(), null, null);
    }

    public EntityViewComp(String id, String entity) {
        this(id, entity, null, null);
    }

    public EntityViewComp(String id, EntityViewType type, Boolean showHead) {
        this(id, type.getName(), showHead, null);
    }

    public EntityViewComp(String id, String entity, Boolean showHead) {
        this(id, entity, showHead, null);
    }

    public EntityViewComp(String id, EntityViewType type, Boolean showHead, Boolean followMouse) {
        this(id, type.getName(), showHead, followMouse);
    }

    public EntityViewComp(String id, String entity, Boolean showHead, Boolean followMouse) {
        super(id);
        this.entity = entity;
        this.showHead = showHead;
        this.followMouse = followMouse;
    }

}
