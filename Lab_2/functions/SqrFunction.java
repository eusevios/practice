package functions;

public class SqrFunction implements MathFunction {
    public double apply(double x){
        return Math.pow(x,2);
    }

    public CompositeFunction andThen(MathFunction afterFunction){
        CompositeFunction newFunction = new CompositeFunction(this, afterFunction);
        return newFunction;
    }
}
