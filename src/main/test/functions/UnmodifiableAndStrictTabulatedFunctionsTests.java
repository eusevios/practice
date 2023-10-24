package functions;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableAndStrictTabulatedFunctionsTests {

    double[] arrX = {-3, 0, 2};
    double[] arrY = {3, 5, 7};

    @Test
    void setYTest() {
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(arrX, arrY);
        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(arrX, arrY);

        UnmodifiableTabulatedFunction unModFunc1 = new UnmodifiableTabulatedFunction(func1);
        UnmodifiableTabulatedFunction unModFunc2 = new UnmodifiableTabulatedFunction(func2);

        assertThrows(UnsupportedOperationException.class, () -> {
            unModFunc1.setY(1, 51);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            unModFunc2.setY(2, 51);
        });
    }

    @Test
    void applyTest() {
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(arrX, arrY);
        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(arrX, arrY);

        StrictTabulatedFunction strictFunc1 = new StrictTabulatedFunction(func1);
        StrictTabulatedFunction strictFunc2 = new StrictTabulatedFunction(func2);

        assertThrows(UnsupportedOperationException.class, () -> {
            strictFunc1.apply(1);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            strictFunc2.apply(5);
        });

        assertDoesNotThrow(() -> strictFunc1.apply(0));
    }

    @Test
    void CombinationTest() {
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(arrX, arrY);

        StrictTabulatedFunction strictFunc = new StrictTabulatedFunction(func1);
        UnmodifiableTabulatedFunction strictUnModFunc1 = new UnmodifiableTabulatedFunction(strictFunc);

        assertThrows(UnsupportedOperationException.class, () -> {
            strictUnModFunc1.apply(1);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            strictUnModFunc1.setY(1, 5);
        });

        UnmodifiableTabulatedFunction unModFunc = new UnmodifiableTabulatedFunction(func1);
        StrictTabulatedFunction strictUnModFunc2 = new StrictTabulatedFunction(unModFunc);

        assertThrows(UnsupportedOperationException.class, () -> {
            strictUnModFunc2.apply(1);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            strictUnModFunc2.setY(1, 5);
        });

    }

    ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(arrX, arrY);
    LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(arrX, arrY);
    UnmodifiableTabulatedFunction unModFunc = new UnmodifiableTabulatedFunction(func1);
    StrictTabulatedFunction strictFunc = new StrictTabulatedFunction(func2);

    @Test
    void getCount() {

        assertEquals(unModFunc.getCount(), 3);
        assertEquals(strictFunc.getCount(), 3);

    }

    @Test
    void getX() {
        assertEquals(unModFunc.getX(0), -3);
        assertEquals(strictFunc.getX(0), -3);
    }

    @Test
    void getY() {
        assertEquals(unModFunc.getY(0), 3);
        assertEquals(strictFunc.getY(0), 3);
    }


    @Test
    void indexOfX() {
        assertEquals(unModFunc.indexOfX(2), 2);
        assertEquals(unModFunc.indexOfX(-513), -1);
        assertEquals(strictFunc.indexOfX(2), 2);
        assertEquals(strictFunc.indexOfX(-513), -1);
    }

    @Test
    void indexOfY() {
        assertEquals(unModFunc.indexOfY(3), 0);
        assertEquals(unModFunc.indexOfY(6315), -1);
        assertEquals(strictFunc.indexOfY(3), 0);
        assertEquals(strictFunc.indexOfY(6315), -1);
    }

    @Test
    void leftBound() {
        assertEquals(unModFunc.leftBound(), -3);
        assertEquals(strictFunc.leftBound(), -3);
    }

    @Test
    void rightBound() {
        assertEquals(unModFunc.rightBound(), 2);
        assertEquals(strictFunc.rightBound(), 2);
    }

    @Test
    void iterator() {

        Iterator<Point> iterator = unModFunc.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, unModFunc.getX(i));
            assertEquals(point.y, unModFunc.getY(i));
            ++i;
        }
        i = 0;
        for (Point point : strictFunc) {
            assertEquals(point.x, strictFunc.getX(i));
            assertEquals(point.y, strictFunc.getY(i));
            ++i;
        }
    }


}