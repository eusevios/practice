package exceptions;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.*;
import operations.TabulatedFunctionOperationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ExceptionsTest {
    @Test
    void ArrayIsNotSortedExceptionTest(){
        assertThrows(ArrayIsNotSortedException.class, ()->{throw new ArrayIsNotSortedException();});
        double[] arrayOfX = {5,4,1};
        double[] arrayOfY = {5,2,4};
        assertThrows(ArrayIsNotSortedException.class, ()->{ TabulatedFunction func = new ArrayTabulatedFunction(arrayOfX, arrayOfY);});
    }
    @Test
    void DifferentLengthOfArraysExceptionTest(){
        assertThrows(DifferentLengthOfArraysException.class, ()->{throw new DifferentLengthOfArraysException();});
        double[] arrayOfX = {5,4,1};
        double[] arrayOfY1 = {5,2,4,5};
        assertThrows(DifferentLengthOfArraysException.class, ()->{ TabulatedFunction func = new ArrayTabulatedFunction(arrayOfX, arrayOfY1);});
    }
    @Test
    void InconsistentFunctionsExceptionTest(){

        assertThrows(InconsistentFunctionsException.class, ()->{throw new InconsistentFunctionsException();});

        double[] array1 = {5,6,14};
        double[] array2 = {5,2,4};
        double[] array3 = {5,6,9,15};
        double[] array4 = {5,2,4,5};
        double[] array5 = {4,3,1};

        TabulatedFunction func1= new ArrayTabulatedFunction(array1, array2);
        TabulatedFunction func2 = new ArrayTabulatedFunction(array3, array4);
        TabulatedFunction func3 = new LinkedListTabulatedFunction(array3,array4);
        TabulatedFunction func4 = new LinkedListTabulatedFunction(array5,array2);

        TabulatedFunctionFactory fact = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService oper = new TabulatedFunctionOperationService(fact);

        assertThrows(InconsistentFunctionsException.class, ()->oper.toAdd(func1,func2));
        assertThrows(InconsistentFunctionsException.class, ()->oper.toSubstract(func1,func4));

        assertDoesNotThrow(()->oper.toAdd(func2,func3));



    }

    @Test
    void InterpolationExceptionTest(){
        assertThrows(InterpolationException.class, ()->{throw new InterpolationException();});
        assertThrows(InterpolationException.class, ()->{throw new InterpolationException("Incorrect index");});
    }
}