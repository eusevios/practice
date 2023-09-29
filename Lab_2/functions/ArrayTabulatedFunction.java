package functions;

import javax.xml.crypto.dsig.XMLValidateContext;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction{
    ArrayTabulatedFunction(double[] xValues, double[] yValues){
        super.count = xValues.length;
        super.arrayOfX = Arrays.copyOf(xValues, super.count);
        super.arrayOfX = Arrays.copyOf(yValues, super.count);

    }
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int countOfThisValues){
        super.arrayOfY = new double[countOfThisValues];
        super.arrayOfX = new double[countOfThisValues];
        super.count = countOfThisValues;
        arrayOfX[0] = xFrom; arrayOfY[0] = source.apply(xFrom);
        arrayOfX[countOfThisValues-1] = xTo; arrayOfY[countOfThisValues-1] = source.apply(xTo);
        double samplingFrequency = (xTo-xFrom)/(countOfThisValues-1);
        double currentX = xFrom+samplingFrequency;
        int current_index = 1;
        while(current_index!=countOfThisValues-1){
            arrayOfX[current_index] = currentX;
            arrayOfY[current_index] = source.apply(currentX);
            ++current_index;
            currentX+=samplingFrequency;
        }
    }
}
