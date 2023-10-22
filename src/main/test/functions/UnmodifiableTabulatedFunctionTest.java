package functions;

import exceptions.DifferentLengthOfArraysException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableTabulatedFunctionTest {

    double[] arrX = {-3,0,2};
    double[] arrY = {3,5,7};

    @Test
    void setYTest() {
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(arrX, arrY);
        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(arrX, arrY);
        UnmodifiableTabulatedFunction unModFunc1 = new UnmodifiableTabulatedFunction(func1);
        UnmodifiableTabulatedFunction unModFunc2 = new UnmodifiableTabulatedFunction(func2);
        assertThrows(UnsupportedOperationException.class, () -> {unModFunc1.setY(1,51);});
        assertThrows(UnsupportedOperationException.class, () -> {unModFunc2.setY(2,51);});
    }
}