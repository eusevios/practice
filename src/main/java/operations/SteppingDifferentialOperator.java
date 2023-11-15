package operations;

import functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    double step;

    public SteppingDifferentialOperator(double step) {
        if (step < 0 || Double.isNaN(step) || Double.isInfinite(step)) throw new IllegalArgumentException();
        this.step = step;
    }

    public double getStep() {
        return this.step;
    }

    public void setStep(double step) {

        this.step = step;

    }
}
