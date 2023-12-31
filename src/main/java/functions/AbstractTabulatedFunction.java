package functions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

import java.io.Serial;
import java.io.Serializable;

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({

        @JsonSubTypes.Type(value = ArrayTabulatedFunction.class, name = "array"),
        @JsonSubTypes.Type(value = LinkedListTabulatedFunction.class, name = "llist"),

})

public abstract class AbstractTabulatedFunction implements TabulatedFunction, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected int count;

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


    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getSimpleName() + " size = " + count + '\n');
        for (Point point : this) {
            str.append("[" + point.x + "; " + point.y + "]" + "\n");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
}
