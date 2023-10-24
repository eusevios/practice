package operations;

import factory.LinkedListTabulatedFunctionFactory;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.execution.DefaultExecutableInvoker;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {

    double[] firstArrayOfX = {1,2,3,4};
    double[] firstArrayOfY = {2,4,8,16};

    @Test
    void setFactory() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator oper = new TabulatedDifferentialOperator();
        oper.setFactory(fact);
        assertTrue(oper.getFactory().getClass() == LinkedListTabulatedFunctionFactory.class);
    }

    @Test
    void derive() {

        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator oper = new TabulatedDifferentialOperator(fact);
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(firstArrayOfX, firstArrayOfY);
        TabulatedFunction diff_func = oper.derive(func);
        assertEquals(2, diff_func.getY(0));
        assertEquals(4, diff_func.getY(1));
        assertEquals(8, diff_func.getY(2));
        assertEquals(8, diff_func.getY(3));

    }
}