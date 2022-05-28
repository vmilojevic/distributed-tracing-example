package org.example.graph;

public class Label {

    private final String name;
    private final int value;
    private final Label prev;

    public Label(String name, int value, Label prev) {
        this.name = name;
        this.value = value;
        this.prev = prev;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public Label getPrev() {
        return prev;
    }
}
