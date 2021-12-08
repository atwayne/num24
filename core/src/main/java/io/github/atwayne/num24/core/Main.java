package io.github.atwayne.num24.core;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var calculator = new Calculator();
        var printer = new ExpressionBuilder();
        var input = List.of(1, 1, 1, 8);
        var solutions = calculator.Calculate(input);
        if (solutions.isEmpty()) {
            System.out.println("No solution found");
            return;
        }
        solutions.stream().map(printer::Build).forEach(System.out::println);
    }
}
