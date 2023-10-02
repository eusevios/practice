package functions;

public interface MathFunction {
    double apply(double x);
    
    default CompositeFunction andThen(MathFunction afterFunction)
    {
        CompositeFunction newFunction = new CompositeFunction(afterFunction, this);
        return newFunction;
    }
}
// newfunc = f1.andthen(f2) = f1(f2)