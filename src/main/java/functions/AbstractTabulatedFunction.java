package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

import java.io.Serial;
import java.io.Serializable;

public abstract class AbstractTabulatedFunction implements TabulatedFunction, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected int count;

    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    static void checkLengthIsTheSame(double[] xValues, double[] yValues) {

        if (xValues.length != yValues.length)
            throw new DifferentLengthOfArraysException("Length of arrays is different");

    }

    static void checkSorted(double[] xValues) {

        int i = 1;
        while (i < xValues.length) {
            if (xValues[i] <= xValues[i - 1]) throw new ArrayIsNotSortedException("Array is not Sorted");
            i++;
        }

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getSimpleName() + " size = " + count + '\n');
        for(Point point : this){
            str.append("[" + point.x + "; " + point.y + "]" + "\n");
        }
        str.deleteCharAt(str.length()-1);
        return str.toString();
    }
}
