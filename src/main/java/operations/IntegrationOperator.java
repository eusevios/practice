package operations;


import concurrent.IntegrationTask;
import functions.Point;
import functions.TabulatedFunction;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IntegrationOperator {

    public static double integrate(TabulatedFunction tabFunc, int k) throws ExecutionException, InterruptedException {

        Point[] points = TabulatedFunctionOperationService.asPoints(tabFunc);

        ArrayList<Integer> lengthList = new ArrayList<>(k);

        for (int i = 0; i < points.length % k; i++) {
            int lengthTread = points.length / k + 1;
            lengthList.add(lengthTread);
        }
        for (int i = 0; i < k - points.length % k; i++) {
            int lengthTread = points.length / k;
            if (lengthTread != 0) lengthList.add(lengthTread);
        }


        ExecutorService service = Executors.newFixedThreadPool(k);
        Future<Double>[] results = new Future[lengthList.size()];
        for (int i = 0, j = 0; i < points.length; i += lengthList.get(j), j++) {
            IntegrationTask task = new IntegrationTask(i, lengthList.get(j), points);
            results[j] = service.submit(task);
        }

        double sum = 0;
        for (Future<Double> result : results) {
            sum += result.get();
        }

        service.shutdown();
        return sum;
    }
}
