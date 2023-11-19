package operations;

import functions.ArrayTabulatedFunction;
import functions.NaturalLogarithm;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationOperatorTest {

    @Test
    void integrate() throws ExecutionException, InterruptedException {

        double[] x1 = {1,2,5};
        double[] y1 = {0,3,2};
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(x1, y1);
        assertEquals(9, IntegrationOperator.integrate(function1));

        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(new NaturalLogarithm(), 1, 10, 1000);
        assertEquals(14.02585, IntegrationOperator.integrate(function2), 0.00001);

        double[] x2 = {2,8,14};
        double[] y2 = {3,-3,3};
        ArrayTabulatedFunction function3 = new ArrayTabulatedFunction(x2, y2);
        assertEquals(18, IntegrationOperator.integrate(function3));

    }
}