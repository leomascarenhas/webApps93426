package ca.vanier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        Double result = Calculator.execute(args);

        System.out.println("The result is: ".concat(String.valueOf(result)));
        
        Logger logger = LoggerFactory.getLogger(Main.class);
    }
}