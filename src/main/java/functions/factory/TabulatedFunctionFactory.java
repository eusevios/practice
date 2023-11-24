package functions.factory;

import functions.MathFunction;
import functions.StrictTabulatedFunction;
import functions.TabulatedFunction;
import functions.UnmodifiableTabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction createWithSecondConstructor(MathFunction function, double xFrom, double xTo, int size);

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues) {

        TabulatedFunction func = this.create(xValues, yValues);
        return new UnmodifiableTabulatedFunction(func);

    }

    default TabulatedFunction createStrict(double[] xValues, double[] yValues) {

        TabulatedFunction func = this.create(xValues, yValues);
        return new StrictTabulatedFunction(func);

    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues) {

        TabulatedFunction func = this.create(xValues, yValues);
        StrictTabulatedFunction strictFunc = new StrictTabulatedFunction(func);
        return new UnmodifiableTabulatedFunction(strictFunc);

    }

}