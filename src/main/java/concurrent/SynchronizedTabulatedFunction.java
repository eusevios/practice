package concurrent;

import functions.Point;
import functions.TabulatedFunction;
import operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {

    TabulatedFunction function;

    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }


    @Override
    public double apply(double x) {
        synchronized (this) {
            return function.apply(x);
        }
    }

    @Override
    public int getCount() {
        synchronized (this) {
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (this) {
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (this) {
            return function.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (this) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (this) {
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (this) {
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (this) {
            return function.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (this) {
            return function.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (this) {
            Point[] pointsList = TabulatedFunctionOperationService.asPoints(function);
            return new Iterator<Point>() {
                int i;

                @Override
                public boolean hasNext() {
                    return i < pointsList.length;
                }

                @Override
                public Point next() {

                    if (!hasNext()) throw new NoSuchElementException();
                    Point point = pointsList[i];
                    ++i;
                    return point;
                }
            };
        }
    }

    <T> T doSynchronously(Operation<T> op) {
        synchronized (this) {
            return op.apply(this);
        }
    }

    public interface Operation<T> {
        public T apply(SynchronizedTabulatedFunction function);
    }
}
