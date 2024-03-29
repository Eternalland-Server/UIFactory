package com.taylorswiftcn.megumi.uifactory.generate.ui.component;

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType;
import com.taylorswiftcn.megumi.uifactory.generate.function.SubmitParams;
import com.taylorswiftcn.megumi.uifactory.util.UIFactoryUtil;
import lombok.Getter;
import lombok.ToString;
import net.sakuragame.eternal.dragoncore.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Sound;

import java.lang.reflect.Field;
import java.util.*;

@Getter
@ToString
public abstract class BasicComponent implements IComponent {

    private String id;

    @ComponentField(define = "x")
    private String x;

    @ComponentField(define = "y")
    private String y;

    @ComponentField(define = "width")
    private String width;

    @ComponentField(define = "height")
    private String height;

    @ComponentField(define = "limitX")
    private String limitX;

    @ComponentField(define = "limitY")
    private String limitY;

    @ComponentField(define = "limitWidth")
    private String limitWidth;

    @ComponentField(define = "limitHeight")
    private String limitHeight;

    @ComponentField(define = "minDistanceX")
    private String minMoveX;

    @ComponentField(define = "maxDistanceX")
    private String maxMoveX;

    @ComponentField(define = "minDistanceY")
    private String minMoveY;

    @ComponentField(define = "maxDistanceY")
    private String maxMoveY;

    @ComponentField(define = "distanceX")
    private String currentMoveX;

    @ComponentField(define = "distanceY")
    private String currentMoveY;

    @ComponentField(define = "visible")
    private String visible;

    @ComponentField(define = "enable")
    private Boolean enable;

    @ComponentField(define = "alpha")
    private Double alpha;

    @ComponentField(define = "scale")
    private String scale;

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

    @ComponentField(define = "extends")
    private String extend;

    private final HashMap<String, String> actions;

    public BasicComponent(String id) {
        this.id = id;
        this.actions = new HashMap<>();
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Map<String, Object> build() {
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

                componentMap.put(define, value);
            }

            if (actions.size() != 0) componentMap.put("actions", actions);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return componentMap;
    }

    public BasicComponent addAction(ActionType type) {
        String eventStatement = type.getParam() == null ?
                String.format("func.Packet_Send('%s', var.screenID, '%s');", type.getEvent().getName(), getID()) :
                String.format("func.Packet_Send('%s', var.screenID, '%s', %s);", type.getEvent().getName(), getID(), StringUtils.replace(type.getParam(), "%comp%", getID()));

        return addAction(type, eventStatement);
    }

    public BasicComponent addAction(ActionType type, String statement) {
        actions.merge(type.getName(), statement, (a, b) -> a + "\n" + b);
        return this;
    }

    public BasicComponent addAction(ActionType type, SubmitParams params) {
        actions.merge(type.getName(), params.getPacket(getID()), (a, b) -> a + "\n" + b);
        return this;
    }

    public BasicComponent addAction(ActionType type, Sound sound) {
        actions.merge(type.getName(), UIFactoryUtil.getPlaySoundFunc(sound), (a, b) -> a + "\n" + b);
        return this;
    }

    public BasicComponent clearAction() {
        actions.clear();
        return this;
    }

    public BasicComponent setX(double x) {
        this.x = "body.x" + (x >= 0 ? "+" + x : String.valueOf(x));
        return this;
    }

    public BasicComponent setY(double y) {
        this.y = "body.y" + (y >= 0 ? "+" + y : String.valueOf(y));
        return this;
    }

    public BasicComponent setX(String x) {
        this.x = x;
        return this;
    }

    public BasicComponent setY(String y) {
        this.y = y;
        return this;
    }

    public BasicComponent setXY(double x, double y) {
        this.x = "body.x" + (x >= 0 ? "+" + x : String.valueOf(x));
        this.y = "body.y" + (y >= 0 ? "+" + y : String.valueOf(y));
        return this;
    }

    public BasicComponent setXY(String x, String y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public BasicComponent setWidth(double width) {
        this.width = String.valueOf(width);
        return this;
    }

    public BasicComponent setHeight(double height) {
        this.height = String.valueOf(height);
        return this;
    }

    public BasicComponent setWidth(String width) {
        this.width = width;
        return this;
    }

    public BasicComponent setHeight(String height) {
        this.height = height;
        return this;
    }

    public BasicComponent setCompSize(double size) {
        this.width = String.valueOf(size);
        this.height = String.valueOf(size);
        return this;
    }

    public BasicComponent setCompSize(double width, double height) {
        this.width = String.valueOf(width);
        this.height = String.valueOf(height);
        return this;
    }

    public BasicComponent setCompSize(String size) {
        this.width = size;
        this.height = size;
        return this;
    }

    public BasicComponent setCompSize(String width, String height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public BasicComponent setLimitX(double limitX) {
        this.limitX = String.valueOf(limitX);
        return this;
    }

    public BasicComponent setLimitY(double limitY) {
        this.limitY = String.valueOf(limitY);
        return this;
    }

    public BasicComponent setLimitX(String limitX) {
        this.limitX = limitX;
        return this;
    }

    public BasicComponent setLimitY(String limitY) {
        this.limitY = limitY;
        return this;
    }

    public BasicComponent setLimitWidth(double limitWidth) {
        this.limitWidth = String.valueOf(limitWidth);
        return this;
    }

    public BasicComponent setLimitHeight(double limitHeight) {
        this.limitHeight = String.valueOf(limitHeight);
        return this;
    }

    public BasicComponent setLimitWidth(String limitWidth) {
        this.limitWidth = limitWidth;
        return this;
    }

    public BasicComponent setLimitHeight(String limitHeight) {
        this.limitHeight = limitHeight;
        return this;
    }

    public BasicComponent setMinMoveX(double minMoveX) {
        return setMinMoveX(String.valueOf(minMoveX));
    }

    public BasicComponent setMaxMoveX(double maxMoveX) {
        return setMaxMoveX(String.valueOf(maxMoveX));
    }

    public BasicComponent setMinMoveX(String minMoveX) {
        this.minMoveX = minMoveX;
        return this;
    }

    public BasicComponent setMaxMoveX(String maxMoveX) {
        this.maxMoveX = maxMoveX;
        return this;
    }

    public BasicComponent setMinMoveY(double minMoveY) {
        return setMinMoveY(String.valueOf(minMoveX));
    }

    public BasicComponent setMaxMoveY(double maxMoveY) {
        return setMaxMoveY(String.valueOf(maxMoveY));
    }

    public BasicComponent setMinMoveY(String minMoveY) {
        this.minMoveY = minMoveY;
        return this;
    }

    public BasicComponent setMaxMoveY(String maxMoveY) {
        this.maxMoveY = maxMoveY;
        return this;
    }

    public BasicComponent setCurrentMoveX(double currentMoveX) {
        return setCurrentMoveX(String.valueOf(currentMoveX));
    }

    public BasicComponent setCurrentMoveY(double currentMoveY) {
        return setCurrentMoveY(String.valueOf(currentMoveY));
    }

    public BasicComponent setCurrentMoveX(String currentMoveX) {
        this.currentMoveX = currentMoveX;
        return this;
    }

    public BasicComponent setCurrentMoveY(String currentMoveY) {
        this.currentMoveY = currentMoveY;
        return this;
    }

    public BasicComponent setVisible(boolean visible) {
        this.visible = String.valueOf(visible);
        return this;
    }

    public BasicComponent setVisible(String visible) {
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
        this.scale = String.valueOf(scale);
        return this;
    }

    public BasicComponent setScale(String scale) {
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

    public BasicComponent setExtend(String extend) {
        this.extend = extend;
        return this;
    }
}
