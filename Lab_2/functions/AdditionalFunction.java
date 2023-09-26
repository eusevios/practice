package functions;

public class AdditionalFunction implements MathFunction{
    public double apply(double x){
        return -1 * (x + Math.PI/2);
    }

    public CompositeFunction andThen(MathFunction afterFunction){
        CompositeFunction newFunction = new CompositeFunction(afterFunction, this);
        return newFunction;
    }

}
