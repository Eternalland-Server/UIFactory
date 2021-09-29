package com.taylorswiftcn.megumi.uifactory.generate.ui.component.base;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "slot")
public class SlotComp extends BasicComponent {

    @ComponentField(define = "identifier")
    private String identifier;

    @ComponentField(define = "drawBackground")
    private Boolean drawBackground;

    public SlotComp(String id, String identifier) {
        super(id);
        this.identifier = identifier;
    }

    public SlotComp setDrawBackground(Boolean drawBackground) {
        this.drawBackground = drawBackground;
        return this;
    }

    public static SlotComp getOriginSlot(int index) {
        if (0 > index || index > 45) return null;
        String identifier = String.format("container_%s", index);
        return new SlotComp(identifier, identifier);
    }

    public SlotComp setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }
}
