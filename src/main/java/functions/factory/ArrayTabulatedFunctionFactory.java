package functions.factory;

import functions.ArrayTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {

        return new ArrayTabulatedFunction(xValues, yValues);

    }

    @Override
    public TabulatedFunction createWithSecondConstructor(MathFunction function, double xFrom, double xTo, int size) {
        return new ArrayTabulatedFunction(function, xFrom, xTo, size);
    }


}