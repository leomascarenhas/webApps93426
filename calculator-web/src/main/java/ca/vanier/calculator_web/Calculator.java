package ca.vanier.calculator_web;

public class Calculator {

    public static Double execute(Operator operator, Double value1, Double value2) throws Exception {

        if (operator.equals(Operator.ADD)) {
            return Calculator.add(value1, value2);
        }

        if (operator.equals(Operator.SUB)) {    
            return Calculator.sub(value1, value2);
        }

        throw new Exception("Operation not supported.");
    }

    public static Double add(Double value1, Double value2) {
        return value1 + value2;
    }

    public static Double sub(Double value1, Double value2) {
        return value1 - value2;
    }


}
