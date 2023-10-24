package operations;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.*;

import javax.swing.text.TabableView;


public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    protected TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    public TabulatedDifferentialOperator(){

        this.factory = new ArrayTabulatedFunctionFactory();

    }
    public TabulatedFunctionFactory getFactory(){

        return this.factory;

    }

    public void setFactory(TabulatedFunctionFactory factory){

        this.factory = factory;

    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function){

        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(function);

        double[] arrayOfX = new double[function.getCount()];
        double[] arrayOfY = new double[function.getCount()];

        int i = 0;
        while(i<arrayOfX.length-1){
            arrayOfX[i] = arrayOfPoints[i].x;
            arrayOfY[i] = (arrayOfPoints[i+1].y-arrayOfPoints[i].y)/(arrayOfPoints[i+1].x-arrayOfPoints[i].x);
            ++i;
        }
        arrayOfX[i] = arrayOfPoints[i].x;
        arrayOfY[i] = arrayOfY[i-1];

        return factory.create(arrayOfX, arrayOfY);

    }
}
