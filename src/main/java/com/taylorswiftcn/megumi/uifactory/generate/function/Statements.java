package com.taylorswiftcn.megumi.uifactory.generate.function;

import java.util.LinkedList;

public class Statements {

    private LinkedList<String> content;

    public Statements() {
        this.content = new LinkedList<>();
    }

    public Statements add(String statement) {
        content.add(statement);
        return this;
    }

    public String build() {
        return String.join("\n", content);
    }
}
