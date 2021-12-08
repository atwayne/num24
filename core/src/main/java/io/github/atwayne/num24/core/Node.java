package io.github.atwayne.num24.core;

public class Node {
    public final Node LeftNode;
    public final Node RightNode;
    public final Operators Operator;
    public Double Value;

    public Node(double value) {
        Value = value;
        Operator = Operators.Equal;
        LeftNode = null;
        RightNode = null;
    }

    public Node(Node left, Node right, Operators operator) {
        LeftNode = left;
        RightNode = right;
        Operator = operator;
        Calculate();
    }

    private void Calculate() {
        if (Value != null) {
            return;
        }
        switch (Operator) {
            case Add -> Value = LeftNode.Value + RightNode.Value;
            case Subtract -> Value = LeftNode.Value - RightNode.Value;
            case Multiply -> Value = LeftNode.Value * RightNode.Value;
            case Divide -> {
                if (RightNode.Value != 0) {
                    Value = LeftNode.Value / RightNode.Value;
                }
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + Operator);
        }
    }
}
