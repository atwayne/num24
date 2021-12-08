package io.github.atwayne.num24.core;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    final Integer Target;
    final Double EPSILON = 1e-6;

    public Calculator() {
        this(24);
    }

    public Calculator(Integer target) {
        Target = target;
    }

    public List<Node> Calculate(List<Integer> input) {
        var sorted = input.stream().sorted().map(Node::new).toList(); // .map(x -> (double) x)
        var solutions = new ArrayList<Node>();
        Search(sorted, solutions);
        return solutions;
    }

    private boolean Search(List<Node> list, List<Node> result) {
        if (list.size() == 0) {
            return false;
        }
        if (list.size() == 1) {
            var lastOne = list.get(0);
            if (Math.abs(Target - lastOne.Value) < EPSILON) {
                result.add(lastOne);
                return true;
            }
            return false;
        }

        for (var i = 0; i < list.size(); i++) {
            for (var j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                var first = list.get(i);
                var second = list.get(j);
                List<Node> others = new ArrayList<>();
                for (var k = 0; k < list.size(); k++) {
                    if (k != i && k != j) {
                        others.add(list.get(k));
                    }
                }

                var combinations = GetCombinations(first, second);
                for (var node : combinations) {
                    others.add(node);
                    Search(others, result);
                    others.remove(others.size() - 1);
                }
            }
        }
        return !result.isEmpty();
    }


    private List<Node> GetCombinations(Node first, Node second) {
        var combinations = List.of(
                new Node(first, second, Operators.Add), // a + b
                new Node(first, second, Operators.Subtract), // a - b
                new Node(second, first, Operators.Subtract), // b - a
                new Node(first, second, Operators.Multiply), // a * b
                new Node(first, second, Operators.Divide), // a / b
                new Node(second, first, Operators.Divide) // b / a
        );
        return combinations.stream().filter(x -> x.Value != null && x.Value > 0).toList();
    }
}
