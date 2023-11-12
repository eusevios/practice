package concurrent;

import functions.ArrayTabulatedFunction;
import functions.Point;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SynchronizedTabulatedFunctionTest {

    double[] X = {2,5,7};
    double[] Y = {-4, 12, 6};

    SynchronizedTabulatedFunction syncTabFunc = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(X,Y));

    @Test
    void methodsTest(){

        assertEquals(12, syncTabFunc.apply(5));
        assertEquals(3, syncTabFunc.getCount());
        assertEquals(7,syncTabFunc.getX(2));
        assertEquals(-4,syncTabFunc.getY(0));
        syncTabFunc.setY(1, -43);
        assertEquals(-43, syncTabFunc.getY(1));
        // {-4,-43,6}
        assertEquals(-1,syncTabFunc.indexOfX(12));
        assertEquals(1,syncTabFunc.indexOfY(-43));
        assertEquals(2,syncTabFunc.leftBound());
        assertEquals(7,syncTabFunc.rightBound());
    }

    @Test
    void iteratorTest(){
        int i = 0;
        for (Point point : syncTabFunc) {
            assertEquals(point.x, syncTabFunc.getX(i));
            assertEquals(point.y, syncTabFunc.getY(i));
            ++i;
        }
    }
}
