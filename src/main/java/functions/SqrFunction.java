package functions;

import ui.Functions;

@Functions(name = "Квадратичная функция y=x^2", priority = 1)
public class SqrFunction implements MathFunction {

    public double apply(double x) {
        return Math.pow(x, 2);
    }

}
