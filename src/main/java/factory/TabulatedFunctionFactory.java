package factory;
import functions.*;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues){
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);
        return new UnmodifiableTabulatedFunction(func);
    }

}
