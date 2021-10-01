package com.taylorswiftcn.megumi.uifactory.generate.function;

import java.util.LinkedList;

public class Statements {

    private LinkedList<String> contents;

    public Statements() {
        this.contents = new LinkedList<>();
    }

    public Statements add(String statement) {
        this.contents.add(statement);
        return this;
    }

    public String build() {
        return String.join("\n", contents);
    }
}
