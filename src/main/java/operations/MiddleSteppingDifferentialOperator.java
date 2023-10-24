package operations;

import functions.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {
    MiddleSteppingDifferentialOperator(double step){
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        MathFunction differentialFunc = (x)->(function.apply(x+step)-function.apply(x-step))/ (2*step);

        return differentialFunc;
    }
}
