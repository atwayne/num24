package io.github.atwayne.num24.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionBuilderTest {

    private ExpressionBuilder expressionBuilder;

    @BeforeEach
    void setup() {
        expressionBuilder = new ExpressionBuilder();
    }

    @Test
    @DisplayName("should print single digit number")
    void should_print_single_digit() {
        // Arrange
        var node = new Node(1);
        var expected = String.valueOf(node.Value.intValue());

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print a + b")
    void should_print_add() {
        // Arrange
        var left = new Node(1);
        var right = new Node(2);
        var node = new Node(left, right, Operators.Add);

        var expected = "1 + 2";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print a - b")
    void should_print_subtract() {
        // Arrange
        var left = new Node(1);
        var right = new Node(2);
        var node = new Node(left, right, Operators.Subtract);

        var expected = "1 - 2";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print a * b")
    void should_print_multiply() {
        // Arrange
        var left = new Node(1);
        var right = new Node(2);
        var node = new Node(left, right, Operators.Multiply);

        var expected = "1 * 2";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print a / b")
    void should_print_divide() {
        // Arrange
        var left = new Node(1);
        var right = new Node(2);
        var node = new Node(left, right, Operators.Divide);

        var expected = "1 / 2";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print a + b + c")
    void should_print_formula() {
        // Arrange
        var first = new Node(1);
        var second = new Node(2);
        var third = new Node(3);

        var left = new Node(first, second, Operators.Add);
        var node = new Node(left, third, Operators.Add);

        var expected = "1 + 2 + 3";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print a * b * c")
    void should_print_formula_2() {
        // Arrange
        var first = new Node(1);
        var second = new Node(2);
        var third = new Node(3);

        var left = new Node(first, second, Operators.Multiply);
        var node = new Node(left, third, Operators.Multiply);

        var expected = "1 * 2 * 3";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print (a + b) * c")
    void should_print_formula_with_parentheses() {
        // Arrange
        var first = new Node(1);
        var second = new Node(2);
        var third = new Node(3);

        var left = new Node(first, second, Operators.Add);
        var node = new Node(left, third, Operators.Multiply);

        var expected = "(1 + 2) * 3";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print 1 / (2 - 3)")
    void should_print_formula_with_parentheses_2() {
        // Arrange
        var first = new Node(1);
        var second = new Node(2);
        var third = new Node(3);

        var right = new Node(second, third, Operators.Subtract);
        var node = new Node(first, right, Operators.Divide);

        var expected = "1 / (2 - 3)";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print a * (b + c) + d")
    void should_print_formula_with_parentheses_3() {
        // Arrange
        var first = new Node(1);
        var second = new Node(2);
        var third = new Node(3);
        var forth = new Node(4);

        var left = new Node(first, new Node(second, third, Operators.Add), Operators.Multiply);
        var node = new Node(left, forth, Operators.Add);

        var expected = "1 * (2 + 3) + 4";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print 3 - 2 - 1")
    void should_print_formula_with_parentheses_4() {
        // Arrange
        var first = new Node(3);
        var second = new Node(2);
        var third = new Node(1);

        var left = new Node(first, second, Operators.Subtract);
        var node = new Node(left, third, Operators.Subtract);

        var expected = "3 - 2 - 1";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print 3 - 2 / 1")
    void should_print_formula_with_parentheses_5() {
        // Arrange
        var first = new Node(3);
        var second = new Node(2);
        var third = new Node(1);

        var right = new Node(second, third, Operators.Divide);
        var node = new Node(first, right, Operators.Subtract);

        var expected = "3 - 2 / 1";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should print (a + b / (c - d)) * e")
    void should_print_formula_with_nested_parentheses() {
        // Arrange
        var first = new Node(1);
        var second = new Node(2);
        var third = new Node(3);
        var forth = new Node(4);
        var fifth = new Node(5);

        var node = new Node(
                new Node(first,
                        new Node(second,
                                new Node(third, forth, Operators.Subtract), // c -d
                                Operators.Divide), // b / (c - d)
                        Operators.Add), // a + b / (c - d)
                fifth,
                Operators.Multiply); // (a + b / (c - d)) * e
        var expected = "(1 + 2 / (3 - 4)) * 5";

        // Act
        String actual = expressionBuilder.Build(node);

        // Assert
        assertEquals(expected, actual);
    }
}