package com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "label")
public class LabelComp extends BasicComponent {

    @ComponentField(define = "texts")
    private List<String> texts;

    @ComponentField(define = "center")
    private Boolean center;

    @ComponentField(define = "font")
    private String textFont;

    public LabelComp(String id, String text) {
        this(id, new ArrayList<>(Collections.singletonList(text)), false);
    }

    public LabelComp(String id, List<String> texts) {
        this(id, texts, false);
    }

    public LabelComp(String id, String text, Boolean center) {
        this(id, new ArrayList<>(Collections.singletonList(text)), center);
    }

    public LabelComp(String id, List<String> texts, Boolean center) {
        super(id);
        this.texts = texts;
        this.center = center;
    }

    public BasicComponent setTextFont(String textFont) {
        this.textFont = textFont;
        return this;
    }
}
