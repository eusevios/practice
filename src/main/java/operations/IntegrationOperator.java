package operations;


import com.sun.source.tree.ArrayAccessTree;
import concurrent.IntegrationTask;
import functions.ArrayTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.math.*;

public class IntegrationOperator {

    private static int pointsInThread(int length, int graduate)
    {
        if (length <= Math.pow(10,graduate+1)){
            return (int)Math.pow(10,graduate); }
    	else{
            return pointsInThread(length, ++graduate); }
    }

    public static double integrate(TabulatedFunction tabFunc) throws ExecutionException, InterruptedException {

        Point[] points = TabulatedFunctionOperationService.asPoints(tabFunc);

        int lengthThread = IntegrationOperator.pointsInThread(tabFunc.getCount(), 2);
        int countThread = (int)Math.ceil((double)(tabFunc.getCount()) / lengthThread);
        ExecutorService service = Executors.newFixedThreadPool(countThread);

        ArrayList<IntegrationTask> callList = new ArrayList<>();

        for (int i = 0, j = 0; i < countThread; i++, j += lengthThread) {
            IntegrationTask task = new IntegrationTask(j, j + lengthThread, points);
            callList.add(task);
        }

        List<Future<Double>> futList = service.invokeAll(callList);

        double sum = 0;
        for(int i = 0; i < countThread; i++){
            sum += futList.get(i).get();
        }

        service.shutdown();
        return sum;
    }
}
