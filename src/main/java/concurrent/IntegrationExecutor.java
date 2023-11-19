package concurrent;

import functions.ArrayTabulatedFunction;
import functions.MathFunction;
import functions.NaturalLogarithm;
import functions.TabulatedFunction;
import operations.IntegrationOperator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class IntegrationExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        MathFunction func = new NaturalLogarithm();
        TabulatedFunction func1 = new ArrayTabulatedFunction(func, 1, 10, 1000);

        System.out.println(IntegrationOperator.integrate(func1));
    }
}
