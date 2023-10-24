package operations;
import exceptions.InconsistentFunctionsException;
import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import java.util.Iterator;
public class TabulatedFunctionOperationService {

    TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory){
        this.factory = factory;
    }

    public TabulatedFunctionOperationService(){
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory(){

        return this.factory;

    }

    public void setFactory(TabulatedFunctionFactory factory){

        this.factory = factory;

    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        Point[] arrayOfPoints = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            arrayOfPoints[i] = point;
            ++i;
        }
        return arrayOfPoints;

    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation){

        if (a.getCount()!=b.getCount()) throw new InconsistentFunctionsException("Different lenghts of arrays");

        Point[] aArray = asPoints(a);
        Point[] bArray = asPoints(b);
        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];

        for(int i = 0; i<a.getCount(); i++){

            if (aArray[i].x == bArray[i].x) xValues[i] = aArray[i].x;
            else throw new InconsistentFunctionsException("X-values is not equals");

            yValues[i] = operation.apply(aArray[i].y, bArray[i].y);

        }

        TabulatedFunction func = factory.create(xValues, yValues);

        return func;

    }

    private interface BiOperation{
        double apply(double u, double v);
    }

    public TabulatedFunction toAdd(TabulatedFunction firstFunction, TabulatedFunction secondFunction ){
        BiOperation operation = (u,v)->u+v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction toSubstract(TabulatedFunction firstFunction, TabulatedFunction secondFunction ){
        BiOperation operation  = (u,v)->u-v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction toMultiply(TabulatedFunction firstFunction, TabulatedFunction secondFunction){
        BiOperation operation  = (u,v)->u*v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction toDivide(TabulatedFunction firstFunction, TabulatedFunction secondFunction){
        BiOperation operation  = (u,v)->u/v;
        return doOperation(firstFunction, secondFunction, operation);
    }



}