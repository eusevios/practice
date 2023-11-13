package concurrent;

import functions.*;
import operations.IntegrationOperator;
import operations.TabulatedFunctionOperationService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class IntegrationExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        MathFunction func = new NaturalLogarithm();
        TabulatedFunction func1 = new ArrayTabulatedFunction(func, 1, 10, 1000);

        System.out.println(IntegrationOperator.integrate(func1));

        Point[] asPoints = TabulatedFunctionOperationService.asPoints(func1);
        int size = asPoints.length;
        double sum = 0;

        for(int i = 0; i < size-1; i++){
            sum+=(asPoints[i].y+asPoints[i+1].y)*(asPoints[i+1].x-asPoints[i].x)/2;
        }

    }
}
