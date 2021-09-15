package com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub;

import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class ImageComp extends BasicComponent {

    @ComponentField(define = "texture")
    private String texture;

    @ComponentField(define = "textureHovered")
    private String textureHovered;

    @ComponentField(define = "textureWidth")
    private Double textureWidth;

    @ComponentField(define = "textureHeight")
    private Double textureHeight;

    @ComponentField(define = "u")
    private Double u;

    @ComponentField(define = "v")
    private Double v;

    @ComponentField(define = "text")
    private String text;

    @ComponentField(define = "color")
    private String textColor;

    @ComponentField(define = "font")
    private String textFont;

    public ImageComp(String id, String texture) {
        this(id, texture, null, null, null);
    }

    public ImageComp(String id, String texture, String textureHovered) {
        this(id, texture, textureHovered, null, null);
    }

    public ImageComp(String id, String texture, String textureHovered, String text) {
        this(id, texture, textureHovered, text, null);
    }

    public ImageComp(String id, String texture, String textureHovered, String text, String textColor) {
        super(id);
        this.texture = texture;
        this.textureHovered = textureHovered;
        this.text = text;
        this.textColor = textColor;
    }

    public BasicComponent setUV(double u, double v) {
        this.u = u;
        this.v = v;
        return this;
    }

    public BasicComponent setTextureSize(double width, double height) {
        this.textureWidth = width;
        this.textureHeight = height;
        return this;
    }

    public BasicComponent setTextFont(String textFont) {
        this.textFont = textFont;
        return this;
    }
}
