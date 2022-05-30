package org.example.graph;

public class BfsLabel<T> {

    private final T node;
    private final int value;

    public BfsLabel(T name, int value) {
        this.node = name;
        this.value = value;
    }

    public T getNode() {
        return this.node;
    }

    public int getValue() {
        return this.value;
    }
}
