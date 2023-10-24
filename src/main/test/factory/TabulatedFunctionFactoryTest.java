package factory;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionFactoryTest {

    double[] arrX = {-3,0,2};
    double[] arrY = {3,5,7};
    TabulatedFunctionFactory fact1 = new LinkedListTabulatedFunctionFactory();
    TabulatedFunctionFactory fact2 = new ArrayTabulatedFunctionFactory();
    TabulatedFunction func1 = fact1.create(arrX, arrY);
    TabulatedFunction func2 = fact2.create(arrX, arrY);
    @Test
    void TabulatedFunctionFactoryTest(){
        assertTrue(func1 instanceof LinkedListTabulatedFunction);
        assertTrue(func2 instanceof ArrayTabulatedFunction);

    }
    @Test
    void UnmodifiableTest(){
        TabulatedFunction func1 = fact1.createUnmodifiable(arrX, arrY);
        TabulatedFunction func2 = fact2.createUnmodifiable(arrX, arrY);
        assertThrows(UnsupportedOperationException.class, ()->func1.setY(1,4));
        assertThrows(UnsupportedOperationException.class, ()->func2.setY(1,4));
    }

    @Test
    void StrictTest(){
        TabulatedFunction func1 = fact1.createStrict(arrX, arrY);
        TabulatedFunction func2 = fact2.createStrict(arrX, arrY);
        assertThrows(UnsupportedOperationException.class, ()->func1.apply(5));
        assertThrows(UnsupportedOperationException.class, ()->func2.apply(5));
    }
    @Test
    void StrictUnmodifiableTest(){
        TabulatedFunction strictUnModFunc = fact1.createStrictUnmodifiable(arrX, arrY);
        assertThrows(UnsupportedOperationException.class, ()->strictUnModFunc.apply(5));
        assertThrows(UnsupportedOperationException.class, ()->strictUnModFunc.setY(0,5));
    }
}