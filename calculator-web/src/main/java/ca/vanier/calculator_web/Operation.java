package ca.vanier.calculator_web;

public class Operation {
    
    private Operator operator;
    private Double value1;
    private Double value2;

    public Operator getOperator() {
        return operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    public Double getValue1() {
        return value1;
    }
    public void setValue1(Double value1) {
        this.value1 = value1;
    }
    public Double getValue2() {
        return value2;
    }
    public void setValue2(Double value2) {
        this.value2 = value2;
    }

}
