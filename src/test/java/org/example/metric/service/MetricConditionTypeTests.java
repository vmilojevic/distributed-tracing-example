package org.example.metric.service;

import org.example.metric.MetricConditionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MetricConditionTypeTests {

    @Test
    void Should_ReturnTrue_When_ValuesAreEqual() {
        Assertions.assertTrue(MetricConditionType.EQUALS.apply(1, 1));
    }

    @Test
    void Should_ReturnFalse_When_ValuesAreNotEqual() {
        Assertions.assertFalse(MetricConditionType.EQUALS.apply(1, 2));
    }

    @Test
    void Should_ReturnTrue_When_FirstValueIsLesserThanSecond() {
        Assertions.assertTrue(MetricConditionType.LESS_THAN.apply(1, 2));
    }

    @Test
    void Should_ReturnFalse_When_ValuesAreEqual() {
        Assertions.assertFalse(MetricConditionType.LESS_THAN.apply(1, 1));
    }

    @Test
    void Should_ReturnFalse_When_FirstValueIsGreaterThanSecond() {
        Assertions.assertFalse(MetricConditionType.LESS_THAN.apply(2, 1));
    }
}
