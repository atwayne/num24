package io.github.atwayne.num24.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {

    private Calculator calculator;
    private ExpressionBuilder expressionBuilder;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
        expressionBuilder = new ExpressionBuilder();
    }

    @Test
    @DisplayName("should resolve 6,6,6,6")
    void should_resolve_case_6_6_6_6() {
        // Arrange
        var input = List.of(6, 6, 6, 6);
        var expected = "6 + 6 + 6 + 6";

        // Act
        var solutions = calculator.Calculate(input);
        var expressions = solutions.stream().map(x -> expressionBuilder.Build(x)).toList();

        // Assert
        assertTrue(expressions.contains(expected));
    }

    @Test
    @DisplayName("should resolve 1,2,3,4")
    void should_resolve_case_1_2_3_4() {
        // Arrange
        var input = List.of(1, 2, 3, 4);
        var expected = "1 * 2 * 3 * 4";

        // Act
        var solutions = calculator.Calculate(input);
        var expressions = solutions.stream().map(x -> expressionBuilder.Build(x)).toList();

        // Assert
        assertTrue(expressions.contains(expected));
    }

    @Test
    @DisplayName("should resolve 2,3,4,5")
    void should_resolve_case_2_3_4_5() {
        // Arrange
        var input = List.of(2, 3, 4, 5);
        var expected = "2 * (3 + 4 + 5)";

        // Act
        var solutions = calculator.Calculate(input);
        var expressions = solutions.stream().map(x -> expressionBuilder.Build(x)).toList();

        // Assert
        assertTrue(expressions.contains(expected));
    }

    @Test
    @DisplayName("should resolve 3,4,5,6")
    void should_resolve_case_3_4_5_6() {
        // Arrange
        var input = List.of(3, 4, 5, 6);
        var expected = "(3 + 5 - 4) * 6";

        // Act
        var solutions = calculator.Calculate(input);
        var expressions = solutions.stream().map(x -> expressionBuilder.Build(x)).toList();

        // Assert
        assertTrue(expressions.contains(expected));
    }

    @Test
    @DisplayName("should resolve 1,4,7,8")
    void should_resolve_case_1_4_7_8() {
        // Arrange
        var input = List.of(1, 4, 7, 8);
        var expected = "(7 - 1) * (8 - 4)";

        // Act
        var solutions = calculator.Calculate(input);
        var expressions = solutions.stream().map(x -> expressionBuilder.Build(x)).toList();

        // Assert
        assertTrue(expressions.contains(expected));
    }

    @Test
    @DisplayName("should not resolve 1,2,1,2")
    void should_not_resolve_case_1_2_1_2() {
        // Arrange
        var input = List.of(1, 2, 1, 2);
        // Act
        var solutions = calculator.Calculate(input);
        // Assert
        assertEquals(0, solutions.size());
    }

    @Test
    @DisplayName("should resolve 1,5,5,5")
    void should_resolve_case_1_5_5_5() {
        // Arrange
        var input = List.of(1, 5, 5, 5);
        var expected = "5 * (5 - 1 / 5)";

        // Act
        var solutions = calculator.Calculate(input);
        var expressions = solutions.stream().map(x -> expressionBuilder.Build(x)).toList();

        // Assert
        assertTrue(expressions.contains(expected));
    }
}