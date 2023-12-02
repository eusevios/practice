package functions;

import ui.Functions;

@Functions(name = "Натуральный логарифм y=ln(x)", priority = 4)
public class NaturalLogarithm implements MathFunction {
    public double apply(double x) {
        return Math.log(x);
    }
}