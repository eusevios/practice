package operations;

import functions.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {

        MathFunction differentialFunc = (x) -> (function.apply(x) - function.apply(x - step)) / step;
        return differentialFunc;

    }
}
