package org.example.metric;

public enum MetricConditionType {

    EQUALS {
        @Override public boolean apply(int x, int y) {
            return x == y;
        }
    },
    LESS_THAN {
        @Override public boolean apply(int x, int y) {
            return x < y;
        }
    };

    public abstract boolean apply(int x, int y);
}
