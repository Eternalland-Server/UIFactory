package com.taylorswiftcn.megumi.uifactory.generate.ui.component.base;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "textbox")
public class TextBoxComp extends BasicComponent {

    @ComponentField(define = "length")
    private Integer length;

    @ComponentField(define = "text")
    private String text;

    @ComponentField(define = "drawBackground")
    private Boolean drawBackground;

    @ComponentField(define = "focused")
    private Boolean focused;

    public TextBoxComp(String id) {
        super(id);
    }

    public TextBoxComp(String id, String text) {
        this(id, null, text, null);
    }

    public TextBoxComp(String id, Integer length) {
        this(id, length, null, null);
    }

    public TextBoxComp(String id, Integer length, String text) {
        this(id, length, text, null);
    }

    public TextBoxComp(String id, Integer length, String text, Boolean focused) {
        super(id);
        this.length = length;
        this.text = text;
        this.focused = focused;
        this.addAction(ActionType.TextChange);
    }

    public TextBoxComp setLength(Integer length) {
        this.length = length;
        return this;
    }

    public TextBoxComp setText(String text) {
        this.text = text;
        return this;
    }

    public TextBoxComp setDrawBackground(Boolean drawBackground) {
        this.drawBackground = drawBackground;
        return this;
    }

    public TextBoxComp setFocused(Boolean focused) {
        this.focused = focused;
        return this;
    }
}
