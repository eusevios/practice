package operations;


import com.sun.source.tree.ArrayAccessTree;
import concurrent.IntegrationTask;
import functions.Point;
import functions.TabulatedFunction;

import java.util.ArrayList;
import java.util.concurrent.*;

public class IntegrationOperator {

    public static double toIntegrate(TabulatedFunction source) throws ExecutionException, InterruptedException {

        Point[] points = TabulatedFunctionOperationService.asPoints(source);

        ExecutorService service = Executors.newFixedThreadPool(10);

        ArrayList<Future<Double>> futList = new ArrayList<>();

        ArrayList<IntegrationTask> callList = new ArrayList<>();

        int size = source.getCount();

        long startTime = System.nanoTime();


        for(int i = 0; i< size/100-1; i+=100){
            IntegrationTask task = new IntegrationTask(i, i+100, points);
            callList.add(task);
        }

        long endTime = System.nanoTime();

        long timeElapsed = endTime - startTime;

        System.out.println(timeElapsed);

//        double sum = 0;
//        for(int i = 0; i<size; i++){
//
//            sum+=futList.get(i).get();
//
//        }

        service.shutdown();

        return 1;





    }

}
