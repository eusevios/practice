package concurrent;

import functions.*;
import operations.IntegrationOperator;
import operations.TabulatedFunctionOperationService;

import java.util.concurrent.ExecutionException;

public class IntegrationExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {



        MathFunction func = new NaturalLogarithm();
        TabulatedFunction func1 = new ArrayTabulatedFunction(func, 1, 10, 1000000);


        System.out.println(IntegrationOperator.toIntegrate(func1));


        Point[] asPoints = TabulatedFunctionOperationService.asPoints(func1);
        int size = asPoints.length-1;
        double sum = 0;

        long startTime = System.nanoTime();

        for(int i = 0; i<100000-1; i++){
            sum+=(asPoints[i].y+asPoints[i+1].y)*(asPoints[i+1].x-asPoints[i].x)/2;
        }

        long endTime = System.nanoTime();

        long timeElapsed = endTime - startTime;

        System.out.println(timeElapsed);





    }
}
