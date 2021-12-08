package io.github.atwayne.num24.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {
    @Test
    @DisplayName("should add a + b")
    void should_add(){
        // Arrange
        var left = new Node(1);
        var right = new Node(2);
        var expected = 3;

        // Act
        var node = new Node(left, right, Operators.Add);
        var actual = node.Value;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should subtract a - b")
    void should_subtract(){
        // Arrange
        var left = new Node(3);
        var right = new Node(1);
        var expected = 2;

        // Act
        var node = new Node(left, right, Operators.Subtract);
        var actual = node.Value;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should multiply a * b")
    void should_multiply(){
        // Arrange
        var left = new Node(2);
        var right = new Node(3);
        var expected = 6;

        // Act
        var node = new Node(left, right, Operators.Multiply);
        var actual = node.Value;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should divide a / b")
    void should_divide(){
        // Arrange
        var left = new Node(6);
        var right = new Node(2);
        var expected = 3;

        // Act
        var node = new Node(left, right, Operators.Divide);
        var actual = node.Value;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should calculate (a + b / (c - d)) * e")
    void should_calculate_with_nested_parentheses(){
        // Arrange
        var first = new Node(1);
        var second = new Node(6);
        var third = new Node(4);
        var forth = new Node(2);
        var fifth = new Node(3);
        var expected = 12; // "(1 + 6 / (4 - 2)) * 3";

        // Act
        var node = new Node(
                new Node(first,
                        new Node(second,
                                new Node(third, forth, Operators.Subtract), // c -d
                                Operators.Divide), // b / (c - d)
                        Operators.Add), // a + b / (c - d)
                fifth,
                Operators.Multiply); // (a + b / (c - d)) * e
        var actual = node.Value;

        // Assert
        assertEquals(expected, actual);
    }
}