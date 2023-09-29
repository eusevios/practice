package functions;

import javax.xml.crypto.dsig.XMLValidateContext;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction{
    private double[] arrayOfX;
    private double[] arrayOfY;
    private int count;
    ArrayTabulatedFunction(double[] xValues, double[] yValues){
        count = xValues.length;
        arrayOfX = Arrays.copyOf(xValues, count);
        arrayOfY = Arrays.copyOf(yValues, count);

    }
}
