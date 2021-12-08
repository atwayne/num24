package io.github.atwayne.num24.core;

import java.util.List;
import java.util.Map;

public class ExpressionBuilder {

    private final Map<Operators, String> OperatorSymbols =
            Map.of(
                    Operators.Add, "+",
                    Operators.Subtract, "-",
                    Operators.Multiply, "*",
                    Operators.Divide, "/"
            );
    private final List<Operators> HighPriorityOperators = List.of(Operators.Multiply, Operators.Divide);
    private final List<Operators> LowPriorityOperators = List.of(Operators.Add, Operators.Subtract);
    private final List<Operators> OrderSensitiveOperators = List.of(Operators.Subtract, Operators.Divide);

    public String Build(Node node) {
        if (node.Operator == Operators.Equal) {
            return PrintDigit(node);
        }
        return PrintWithParentheses(node);
    }

    private String PrintOperator(Node node) {
        if (OperatorSymbols.containsKey(node.Operator)) {
            return OperatorSymbols.get(node.Operator);
        }
        return "";
    }

    private String PrintDigit(Node node) {
        return String.valueOf(node.Value.intValue());
    }

    private boolean ShouldPrintParentheses(Node currentNode, Node childNode) {
        if (childNode.Operator == Operators.Equal) {
            return false;
        }

        if (LowPriorityOperators.contains(childNode.Operator) && HighPriorityOperators.contains(currentNode.Operator)) {
            // Example: 5 * (1 + 2)
            return true;
        }
        if (HighPriorityOperators.contains(childNode.Operator) && LowPriorityOperators.contains(currentNode.Operator)) {
            // Example: 3 - 2 / 1
            return false;
        }
        var isLeftChild = currentNode.LeftNode == childNode;
        if (isLeftChild) {
            // Example: (5 - 2) - 3 should be 5 - 2 - 3
            return false;
        }

        // Example:
        // 5 - (3 - 2)
        // 12 / (6 / 2)
        // 1 + (2 + 3) should be 1 + 2 + 3
        // 1 * (2 * 3) should be 1 * 2 * 3
        return OrderSensitiveOperators.contains(currentNode.Operator);
    }

    private String PrintWithParentheses(Node node) {
        var left = Build(node.LeftNode);
        if (ShouldPrintParentheses(node, node.LeftNode)) {
            left = String.format("(%s)", left);
        }
        var right = Build(node.RightNode);
        if (ShouldPrintParentheses(node, node.RightNode)) {
            right = String.format("(%s)", right);
        }
        return String.format("%s %s %s", left, PrintOperator(node), right);
    }
}
