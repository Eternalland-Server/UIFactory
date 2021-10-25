package com.taylorswiftcn.megumi.uifactory.generate.function;

import com.taylorswiftcn.megumi.uifactory.event.UIFEvent;

import java.util.LinkedList;

public class SubmitParams {

    private String condition;
    private LinkedList<String> params;

    public SubmitParams() {
        this.params = new LinkedList<>();
    }

    public SubmitParams(LinkedList<String> params) {
        this.params = params;
    }

    public SubmitParams add(String param) {
        this.params.add(param);
        return this;
    }

    public SubmitParams setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getParams(int index) {
        return params.get(index);
    }

    public String getPacket(String compID) {
        String params2str = String.join(", ", params);
        return (condition == null) ?
                String.format("func.Packet_Send('%s', var.screenID, '%s', %s);", UIFEvent.UIFCompSubmitEvent.getName(), compID, params2str) :
                String.format("(%s) ? { func.Packet_Send('%s', var.screenID, '%s', %s); } : {};", condition, UIFEvent.UIFCompSubmitEvent.getName(), compID, params2str);
    }
}
