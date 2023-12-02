package functions;

import ui.Functions;
@Functions(name = "Прямая y=-1*(x + pi/2)", priority = 2)
public class AdditionalFunction implements MathFunction {
    public double apply(double x) {
        return -1 * (x + Math.PI / 2);
    }
}