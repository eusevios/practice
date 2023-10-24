package factory;
import functions.*;

import java.util.Iterator;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues){

        TabulatedFunction func = this.create(xValues, yValues);
        return new UnmodifiableTabulatedFunction(func);

    }

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){

        TabulatedFunction func = this.create(xValues, yValues);
        return new StrictTabulatedFunction(func);

    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues){

        TabulatedFunction func = this.create(xValues, yValues);
        StrictTabulatedFunction strictFunc = new StrictTabulatedFunction(func);
        return new UnmodifiableTabulatedFunction(strictFunc);

    }

}