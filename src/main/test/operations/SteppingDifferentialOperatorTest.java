package operations;

import functions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SteppingDifferentialOperatorTest {
MathFunction addFunc = new AdditionalFunction();
MathFunction sqrt = new SqrFunction();
MathFunction log = new NaturalLogarithm();
double[] arrayOfX = {3,4,5};
double[] arrayOfY = {6,7,8};
MathFunction tab = new ArrayTabulatedFunction(arrayOfX, arrayOfY);


    @Test
    void LeftSteppingDifferentialOperatorTest(){

        SteppingDifferentialOperator leftOper = new LeftSteppingDifferentialOperator(5);
        MathFunction differentialAddFunc = leftOper.derive(addFunc);
        MathFunction differentialSqrt = leftOper.derive(sqrt);
        MathFunction differentialLog = leftOper.derive(log);
        MathFunction differentialTab = leftOper.derive(tab);

        assertEquals(9, differentialSqrt.apply(7));

        leftOper.setStep(250.5311);

        assertEquals(-1,differentialAddFunc.apply(0));

        leftOper.setStep(Math.E);

        assertEquals(0.10071,differentialLog.apply(11.35), 0.00001);

        leftOper.setStep(1);

        assertEquals(1,differentialTab.apply(4));
    }

    @Test
    void RightSteppingDifferentialOperatorTest(){

        SteppingDifferentialOperator rightOper = new RightSteppingDifferentialOperator(3);
        MathFunction differentialAddFunc = rightOper.derive(addFunc);
        MathFunction differentialSqrt = rightOper.derive(sqrt);
        MathFunction differentialLog = rightOper.derive(log);
        MathFunction differentialTab = rightOper.derive(tab);

        assertEquals(13, differentialSqrt.apply(5));

        rightOper.setStep(250.5311);

        assertEquals(-1,differentialAddFunc.apply(0));

        rightOper.setStep(Math.pow(Math.E,2));

        assertEquals(0.05420,differentialLog.apply(15), 0.00001);

        rightOper.setStep(1);

        assertEquals(1,differentialTab.apply(4));

    }
    @Test
    void MiddleSteppingDifferentialOperatorTest(){

        SteppingDifferentialOperator middleOper = new MiddleSteppingDifferentialOperator(3);
        MathFunction differentialAddFunc = middleOper.derive(addFunc);
        MathFunction differentialSqrt = middleOper.derive(sqrt);
        MathFunction differentialLog = middleOper.derive(log);
        MathFunction differentialTab = middleOper.derive(tab);

        assertEquals(10, differentialSqrt.apply(5));

        middleOper.setStep(250.5311);

        assertEquals(-1,differentialAddFunc.apply(0));

        middleOper.setStep(Math.pow(Math.E,2));

        assertEquals(Math.pow(Math.E,2), middleOper.getStep());

        assertEquals(0.07301,differentialLog.apply(15), 0.00001);

        middleOper.setStep(1);

        assertEquals(1,differentialTab.apply(4));


    }

}