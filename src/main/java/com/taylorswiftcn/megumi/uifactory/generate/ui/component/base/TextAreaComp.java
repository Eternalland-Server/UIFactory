package com.taylorswiftcn.megumi.uifactory.generate.ui.component.base;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "textarea")
public class TextAreaComp extends TextBoxComp {

    public TextAreaComp(String id) {
        super(id);
    }

    public TextAreaComp(String id, String text) {
        super(id, text);
    }

    public TextAreaComp(String id, Integer length) {
        super(id, length);
    }

    public TextAreaComp(String id, Integer length, String text) {
        super(id, length, text);
    }

    public TextAreaComp(String id, Integer length, String text, Boolean focused) {
        super(id, length, text, focused);
    }
}
