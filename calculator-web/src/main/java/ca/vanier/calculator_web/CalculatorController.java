package ca.vanier.calculator_web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calc")
public class CalculatorController {
    
    @GetMapping("/add")
    public String sum(@RequestParam Double value1, @RequestParam Double value2) throws Exception {
        Double result = Calculator.execute(Operator.ADD, value1, value2);
        return String.format("The result is this: %s", result);
    }

    @GetMapping("/")
    public String execute(@RequestBody Operation operation) throws Exception {
        Double result = Calculator.execute(
            operation.getOperator(),
            operation.getValue1(),
            operation.getValue2());

        return String.format("The result is this: %s", result);
    }

    @GetMapping("/getniceperson")
    public String getNicePerson(@RequestParam String name) {
        return name.concat(" is a nice person!");
    }
    
    // Will not available via HTTP request
    public String notMappedMethod() {
        return "Success";
    }

}
