package concurrent;

import functions.MathFunction;
import functions.Point;
import functions.TabulatedFunction;

import java.util.Iterator;

public class SynchronizedTabulatedFunction implements TabulatedFunction {

    TabulatedFunction function;

    public SynchronizedTabulatedFunction(TabulatedFunction function){
        this.function = function;
    }


    @Override
    public double apply(double x) {
        synchronized (this){
            return function.apply(x);
        }
    }

    @Override
    public int getCount() {
        synchronized (this){
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (this){
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (this){
            return function.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (this){
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (this){
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (this){
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (this){
            return function.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (this){
            return function.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (this){
            return function.iterator();
        }
    }

//    <T> T doSynchronously(Operation<? > op){
//        return op.apply(this);
//    }
//
//    public interface Operation<T>{
//        public T apply(SynchronizedTabulatedFunction function);
//    }
}
