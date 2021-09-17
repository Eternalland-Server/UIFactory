package com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.BasicComponent;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.ComponentField;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@ComponentField(define = "texture")
public class ButtonComp extends ImageComp {

    private String textureClicked;

    public ButtonComp(String id, String texture, String textureClicked) {
        this(id, texture, textureClicked, null, null, null);
    }

    public ButtonComp(String id, String texture, String textureClicked, String textureHovered, String text, String textColor) {
        super(id, texture, textureHovered, text, textColor);
        this.textureClicked = textureClicked;
        this.addAction(ActionType.Left_Click);
        this.addAction(ActionType.Right_Click);
        this.addAction(ActionType.Left_Release);
        this.addAction(ActionType.Right_Release);
    }

    public BasicComponent setTextureClicked(String textureClicked) {
        this.textureClicked = textureClicked;
        return this;
    }

    @Override
    public Map<String, Object> buildAction() {
        LinkedHashMap<String, Object> actionsMap = new LinkedHashMap<>();
        if (getActions().size() == 0) return null;

        for (ActionType action : getActions()) {
            if (action.equals(ActionType.Left_Click) || action.equals(ActionType.Right_Click)) {
                if (textureClicked != null) {
                    String sendPacketFunc = String.format("func.Packet_Send('%s', '%s', %s);", action.getEvent().getName(), getID(), action.getParam());
                    String setComponentFunc = String.format("func.component_set('%s', 'texture', '%s');", getID(), textureClicked);
                    actionsMap.put(action.getName(), sendPacketFunc + "\n" + setComponentFunc);
                    continue;
                }
            }

            if (action.equals(ActionType.Left_Release) || action.equals(ActionType.Right_Release)) {
                if (textureClicked != null) {
                    String sendPacketFunc = String.format("func.Packet_Send('%s', '%s', %s);", action.getEvent().getName(), getID(), action.getParam());
                    String setComponentFunc = String.format("func.component_set('%s', 'texture', '%s');", getID(), getTexture());
                    actionsMap.put(action.getName(), sendPacketFunc + "\n" + setComponentFunc);
                    continue;
                }
            }

            if (action.getParam() == null) {
                actionsMap.put(action.getName(), String.format("func.Packet_Send('%s', '%s');", action.getEvent().getName(), getID()));
            }
            else {
                actionsMap.put(action.getName(), String.format("func.Packet_Send('%s', '%s', %s);", action.getEvent().getName(), getID(), StringUtils.replace(action.getParam(), "%comp%", getID())));
            }
        }

        return actionsMap;
    }
}
