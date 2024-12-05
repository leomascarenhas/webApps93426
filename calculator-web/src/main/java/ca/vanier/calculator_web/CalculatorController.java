package ca.vanier.calculator_web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calc")
public class CalculatorController {
    
    @GetMapping("/sum")
    public String getMethodName() {
        return "Success";
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
