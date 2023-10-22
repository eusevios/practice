package factory;

import functions.AbstractTabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionFactoryTest {

    double[] arrX = {-3,0,2};
    double[] arrY = {3,5,7};
    @Test
    void TabulatedFunctionFactoryTest(){

        TabulatedFunctionFactory fact1 = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionFactory fact2 = new ArrayTabulatedFunctionFactory();
        TabulatedFunction func1 = fact1.create(arrX, arrY);
        TabulatedFunction func2 = fact2.create(arrX, arrY);

        assertTrue(func1 instanceof LinkedListTabulatedFunction);
        assertTrue(func2 instanceof ArrayTabulatedFunction);

    }
}