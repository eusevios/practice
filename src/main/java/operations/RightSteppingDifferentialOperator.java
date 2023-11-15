package operations;

import functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {

    RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        MathFunction differentialFunc = (x) -> (function.apply(x + step) - function.apply(x)) / step;
        return differentialFunc;
    }
}
