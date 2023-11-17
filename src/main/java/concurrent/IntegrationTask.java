package concurrent;

import functions.Point;

import java.util.concurrent.Callable;


public class IntegrationTask implements Callable<Double> {

    int from, length;
    Point[] points;


    public IntegrationTask(int from, int length, Point[] points) {

        this.from = from;
        this.length = length;
        this.points = points;
    }

    @Override
    public Double call() {

        double partialSum = 0; int condition = from + length;
        if(from + length == points.length){ --condition; }
        for(int i = from; i < condition; i++){
            partialSum += Math.abs((points[i].y + points[i + 1].y) * (points[i + 1].x - points[i].x) / 2);
        }
        return partialSum;
    }

}
