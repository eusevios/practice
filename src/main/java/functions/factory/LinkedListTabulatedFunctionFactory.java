package functions.factory;

import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {

        return new LinkedListTabulatedFunction(xValues, yValues);

    }

    @Override
    public TabulatedFunction createWithSecondConstructor(MathFunction function, double xFrom, double xTo, int size) {

        return new LinkedListTabulatedFunction(function, xFrom, xTo, size);

    }
}