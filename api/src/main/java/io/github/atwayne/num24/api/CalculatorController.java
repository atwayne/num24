package io.github.atwayne.num24.api;

import io.github.atwayne.num24.core.Calculator;
import io.github.atwayne.num24.core.ExpressionBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    @GetMapping
    public String Help() {
        return "Hello!";
    }

    @PostMapping
    public List<String> Calculate(@RequestBody List<Integer> input) {
        var calculator = new Calculator();
        var expressionBuilder = new ExpressionBuilder();
        var solutions = calculator.Calculate(input);
        return solutions.stream().map(expressionBuilder::Build).toList();
    }
}
