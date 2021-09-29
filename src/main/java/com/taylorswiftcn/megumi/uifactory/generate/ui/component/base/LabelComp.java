package com.taylorswiftcn.megumi.uifactory.generate.ui.component.base;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
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

    public LabelComp setTexts(List<String> texts) {
        this.texts = texts;
        return this;
    }

    public LabelComp setTexts(String text) {
        this.texts = new ArrayList<>(Collections.singletonList(text));
        return this;
    }

    public LabelComp setCenter(Boolean center) {
        this.center = center;
        return this;
    }

    public LabelComp setTextFont(String textFont) {
        this.textFont = textFont;
        return this;
    }
}
