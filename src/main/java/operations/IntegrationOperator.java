package operations;


import concurrent.IntegrationTask;
import functions.Point;
import functions.TabulatedFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class IntegrationOperator {

    public static double integrate(TabulatedFunction tabFunc) throws ExecutionException, InterruptedException {

        Point[] points = TabulatedFunctionOperationService.asPoints(tabFunc);

        int countThread = Runtime.getRuntime().availableProcessors();
        ArrayList<Integer> lengthList = new ArrayList<>(countThread);

        for(int i = 0; i < points.length % countThread; i++){
            int lengthTread = points.length / countThread + 1;
            lengthList.add(lengthTread);
        }
        for(int i = 0; i < countThread - points.length % countThread; i++){
            int lengthTread = points.length / countThread;
            if (lengthTread != 0) lengthList.add(lengthTread);
        }


        ExecutorService service = Executors.newFixedThreadPool(countThread);
        Future<Double>[] results = new Future[lengthList.size()];
        for (int i = 0, j = 0; i < points.length; i += lengthList.get(j), j++) {
            IntegrationTask task = new IntegrationTask(i, lengthList.get(j), points);
            results[j] = service.submit(task);
        }

        double sum = 0;
        for(int i = 0; i < results.length; i++){
            sum += results[i].get();
        }

        service.shutdown();
        return sum;
    }
}
