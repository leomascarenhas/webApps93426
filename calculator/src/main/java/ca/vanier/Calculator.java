package ca.vanier;

public class Calculator {

    public static Double execute(String args[]) throws Exception {
        String operator = args[0];
        Double value1 = Double.valueOf(args[1]);
        Double value2 = Double.valueOf(args[2]);

        if (operator.equals("sum")) {    
            return Calculator.sum(value1, value2);
        }

        throw new Exception("Operation not supported.");
    }

    public static Double sum(Double value1, Double value2) {
        return value1 + value2;
    }

}
