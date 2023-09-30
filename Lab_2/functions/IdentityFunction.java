package functions;

class IdentityFunction implements MathFunction
{
    public double apply(double x)
    {
        return x;
    }

    public CompositeFunction andThen(MathFunction afterFunction){
        CompositeFunction newFunction = new CompositeFunction(afterFunction, this);
        return newFunction;
    }
}