package com.taylorswiftcn.megumi.uifactory.generate.ui.component;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.sub.SlotComp;
import eos.moe.dragoncore.network.PacketSender;
import eos.moe.dragoncore.util.Utils;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.*;

@Getter
@ToString
public abstract class BasicComponent implements IComponent {

    private String id;

    @ComponentField(define = "x")
    private Double x;

    @ComponentField(define = "y")
    private Double y;

    @ComponentField(define = "width")
    private Double width;

    @ComponentField(define = "height")
    private Double height;

    @ComponentField(define = "limitX")
    private Double limitX;

    @ComponentField(define = "limitY")
    private Double limitY;

    @ComponentField(define = "limitWidth")
    private Double limitWidth;

    @ComponentField(define = "limitHeight")
    private Double limitHeight;

    @ComponentField(define = "minDistanceX")
    private Double minMoveX;

    @ComponentField(define = "maxDistanceX")
    private Double maxMoveX;

    @ComponentField(define = "minDistanceY")
    private Double minMoveY;

    @ComponentField(define = "maxDistanceY")
    private Double maxMoveY;

    @ComponentField(define = "distanceX")
    private Double currentMoveX;

    @ComponentField(define = "distanceY")
    private Double currentMoveY;

    @ComponentField(define = "visible")
    private Boolean visible;

    @ComponentField(define = "enable")
    private Boolean enable;

    @ComponentField(define = "alpha")
    private Double alpha;

    @ComponentField(define = "scale")
    private Double scale;

    @ComponentField(define = "tip")
    private List<String> tip;

    @ComponentField(define = "fscale")
    private Double centerScale;

    @ComponentField(define = "frotatex")
    private Double centerRotateX;

    @ComponentField(define = "frotatey")
    private Double centerRotateY;

    @ComponentField(define = "frotatez")
    private Double centerRotateZ;

    @ComponentField(define = "fx")
    private Double offsetX;

    @ComponentField(define = "fy")
    private Double offsetY;

    private List<ActionType> actions;

    public BasicComponent(String id) {
        this.id = id;
        this.actions = new ArrayList<>();
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Map<String, Object> build(Player player) {
        if (this instanceof SlotComp) {
            SlotComp comp = (SlotComp) this;
            if (!comp.getIdentifier().startsWith("container_")) {
                PacketSender.putClientSlotItem(player, comp.getIdentifier(), comp.getItem());
            }
        }

        LinkedHashMap<String, Object> componentMap = new LinkedHashMap<>();

        try {
            ComponentField compFiled = this.getClass().getAnnotation(ComponentField.class);
            if (compFiled == null) throw new RuntimeException(this.getClass() + " 未定义ComponentField");

            componentMap.put("type", compFiled.define());
            List<Field> fields = Utils.getDeclaredFields(this.getClass());
            for (Field field : fields) {
                ComponentField annotation = field.getAnnotation(ComponentField.class);
                if (annotation == null) continue;

                field.setAccessible(true);

                String define = annotation.define();
                Object value = field.get(this);

                if (value == null) continue;

                if (define.equals("x")) {
                    double x = (double) value;
                    componentMap.put(define, String.format("body.x%s", x > 0 ? "+" + x : String.valueOf(x)));
                    continue;
                }
                if (define.equals("y")) {
                    double y = (double) value;
                    componentMap.put(define, String.format("body.y%s", y > 0 ? "+" + y : String.valueOf(y)));
                    continue;
                }

                componentMap.put(define, value);
            }

            LinkedHashMap<String, Object> actionsMap = new LinkedHashMap<>();
            componentMap.put("actions", actionsMap);

            for (ActionType action : actions) {
                if (action.getParam() == null) {
                    actionsMap.put(action.getName(), String.format("func.Packet_Send('%s','%s');", action.getEvent().getName(), getID()));
                }
                else {
                    actionsMap.put(action.getName(), String.format("func.Packet_Send('%s','%s', %s);", action.getEvent().getName(), getID(), StringUtils.replace(action.getParam(), "%comp%", getID())));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return componentMap;
    }

    public void addAction(ActionType type) {
        if (actions.contains(type)) return;
        actions.add(type);
    }

    public BasicComponent setX(double x) {
        this.x = x;
        return this;
    }

    public BasicComponent setY(double y) {
        this.y = y;
        return this;
    }

    public BasicComponent setWidth(double width) {
        this.width = width;
        return this;
    }

    public BasicComponent setHeight(double height) {
        this.height = height;
        return this;
    }

    public BasicComponent setCompSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public BasicComponent setLimitX(double limitX) {
        this.limitX = limitX;
        return this;
    }

    public BasicComponent setLimitY(double limitY) {
        this.limitY = limitY;
        return this;
    }

    public BasicComponent setLimitWidth(double limitWidth) {
        this.limitWidth = limitWidth;
        return this;
    }

    public BasicComponent setLimitHeight(double limitHeight) {
        this.limitHeight = limitHeight;
        return this;
    }

    public BasicComponent setMinMoveX(double minMoveX) {
        this.minMoveX = minMoveX;
        return this;
    }

    public BasicComponent setMaxMoveX(double maxMoveX) {
        this.maxMoveX = maxMoveX;
        return this;
    }

    public BasicComponent setMinMoveY(double minMoveY) {
        this.minMoveY = minMoveY;
        return this;
    }

    public BasicComponent setMaxMoveY(double maxMoveY) {
        this.maxMoveY = maxMoveY;
        return this;
    }

    public BasicComponent setCurrentMoveX(double currentMoveX) {
        this.currentMoveX = currentMoveX;
        return this;
    }

    public BasicComponent setCurrentMoveY(double currentMoveY) {
        this.currentMoveY = currentMoveY;
        return this;
    }

    public BasicComponent setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public BasicComponent setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public BasicComponent setAlpha(double alpha) {
        this.alpha = alpha;
        return this;
    }

    public BasicComponent setScale(double scale) {
        this.scale = scale;
        return this;
    }

    public BasicComponent setTip(String tip) {
        this.tip = new ArrayList<>(Collections.singletonList(tip));
        return this;
    }

    public BasicComponent setTip(List<String> tip) {
        this.tip = tip;
        return this;
    }

    public BasicComponent setCenterScale(double centerScale) {
        this.centerScale = centerScale;
        return this;
    }

    public BasicComponent setCenterRotateX(double centerRotateX) {
        this.centerRotateX = centerRotateX;
        return this;
    }

    public BasicComponent setCenterRotateY(double centerRotateY) {
        this.centerRotateY = centerRotateY;
        return this;
    }

    public BasicComponent setCenterRotateZ(double centerRotateZ) {
        this.centerRotateZ = centerRotateZ;
        return this;
    }

    public BasicComponent setOffsetX(double offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    public BasicComponent setOffsetY(double offsetY) {
        this.offsetY = offsetY;
        return this;
    }
}
