package concurrent;

import functions.Point;
import functions.TabulatedFunction;

import java.io.PipedInputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.*;

public class IntegrationTask implements Callable<Double> {

    int from, to;
    Point[] points;


    public IntegrationTask( int from, int to, Point[] points){

        this.from = from;
        this.to = to;
        this.points=points;
    }

    @Override
    public Double call() {

        double partialSum = 0;
        for(int i = from; i < to - 1; i++){
            partialSum+=(points[i].y+points[i+1].y)*(points[i+1].x-points[i].x)/2;
        }
        return partialSum;
    }

}
