package ca.vanier.calculator_web;

public enum Operator {
    ADD("add"),
    SUB("sub");

    public final String label;

    private Operator(String label) {
        this.label = label;
    }
}
