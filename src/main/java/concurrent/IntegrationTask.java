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

        double partialSum = 0;
        int condition = from + length;
        if (from + length == points.length) {
            --condition;
        }
        for (int i = from; i < condition; i++) {
            if (points[i].y * points[i + 1].y < 0) {
                double x0 = (-points[i].y / (points[i + 1].y - points[i].y)) * (points[i + 1].x - points[i].x) + points[i].x;
                partialSum += Math.abs((points[i].y) * (x0 - points[i].x) / 2);
                partialSum += Math.abs((points[i + 1].y) * (points[i].x - x0) / 2);

            } else {
                partialSum += Math.abs((points[i].y + points[i + 1].y) * (points[i + 1].x - points[i].x) / 2);
            }
        }
        return partialSum;
    }

}
