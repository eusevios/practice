package operations;

import functions.ArrayTabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import functions.*;
import factory.*;

class TabulatedFunctionOperationServiceTest {

    double[] firstArrayOfX = {1,4.3,5,31.53,40};
    double[] firstArrayOfY = {5,10.53,31,2,-5};
    double[] secondArrayOfY = {14,31,3,22,85};

    ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(firstArrayOfX, firstArrayOfY);
    ArrayTabulatedFunction func2 = new ArrayTabulatedFunction(firstArrayOfX, secondArrayOfY);
    LinkedListTabulatedFunction func3 = new LinkedListTabulatedFunction(firstArrayOfX, secondArrayOfY);
    LinkedListTabulatedFunction func4 = new LinkedListTabulatedFunction(firstArrayOfX, firstArrayOfY);

    @Test
    void asPoints() {

        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(firstArrayOfX, firstArrayOfY);
        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(func1);

        int i = 0;
        for(Point point: arrayOfPoints){
            assertEquals(point.x, firstArrayOfX[i]);
            assertEquals(point.y, firstArrayOfY[i]);
            ++i;

        }

    }

    TabulatedFunctionFactory fact1 = new LinkedListTabulatedFunctionFactory();
    TabulatedFunctionOperationService oper1 = new TabulatedFunctionOperationService(fact1);
    TabulatedFunctionOperationService oper2 = new TabulatedFunctionOperationService();

    @Test
    void getTest(){
        assertDoesNotThrow(()->oper2.setFactory(fact1));
        assertEquals(LinkedListTabulatedFunctionFactory.class, oper2.getFactory().getClass());
    }

    @Test
    void additionTest(){

        TabulatedFunction result1 = oper1.toAdd(func1, func2);
        for(int i = 0; i<result1.getCount(); i++){
            assertEquals(firstArrayOfY[i]+secondArrayOfY[i], result1.getY(i));
        }

        TabulatedFunction result2 = oper2.toAdd(func3, func4);
        for(int i = 0; i<result2.getCount(); i++){
            assertEquals(firstArrayOfY[i]+secondArrayOfY[i], result2.getY(i));
        }


        TabulatedFunction result3 = oper2.toAdd(func1, func3);
        for(int i = 0; i<result3.getCount(); i++){
            assertEquals(firstArrayOfY[i]+secondArrayOfY[i], result3.getY(i));
        }


    }

    @Test
    void SubstractionTest(){
        TabulatedFunction result1 = oper1.toSubstract(func1, func2);
        for(int i = 0; i<result1.getCount(); i++){
            assertEquals(firstArrayOfY[i]-secondArrayOfY[i], result1.getY(i));
        }

        TabulatedFunction result2 = oper2.toSubstract(func4, func3);
        for(int i = 0; i<result2.getCount(); i++){
            assertEquals(firstArrayOfY[i]-secondArrayOfY[i], result2.getY(i));
        }


        TabulatedFunction result3 = oper2.toSubstract(func1, func3);
        for(int i = 0; i<result3.getCount(); i++){
            assertEquals(firstArrayOfY[i]-secondArrayOfY[i], result3.getY(i));
        }
    }

    @Test
    void MultiplyTest(){
        TabulatedFunction result1 = oper1.toMultiply(func1, func2);
        for(int i = 0; i<result1.getCount(); i++){
            assertEquals(firstArrayOfY[i]*secondArrayOfY[i], result1.getY(i));
        }

        TabulatedFunction result2 = oper2.toMultiply(func4, func3);
        for(int i = 0; i<result2.getCount(); i++){
            assertEquals(firstArrayOfY[i]*secondArrayOfY[i], result2.getY(i));
        }


        TabulatedFunction result3 = oper2.toMultiply(func1, func3);
        for(int i = 0; i<result3.getCount(); i++){
            assertEquals(firstArrayOfY[i]*secondArrayOfY[i], result3.getY(i));
        }
    }

    @Test
    void DivideTest(){
        TabulatedFunction result1 = oper1.toDivide(func1, func2);
        for(int i = 0; i<result1.getCount(); i++){
            assertEquals(firstArrayOfY[i]/secondArrayOfY[i], result1.getY(i));
        }

        TabulatedFunction result2 = oper2.toDivide(func4, func3);
        for(int i = 0; i<result2.getCount(); i++){
            assertEquals(firstArrayOfY[i]/secondArrayOfY[i], result2.getY(i));
        }


        TabulatedFunction result3 = oper2.toDivide(func1, func3);
        for(int i = 0; i<result3.getCount(); i++){
            assertEquals(firstArrayOfY[i]/secondArrayOfY[i], result3.getY(i));
        }
    }

}