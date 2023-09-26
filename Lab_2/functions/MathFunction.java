package functions;

public interface MathFunction {
    double apply(double x);
    CompositeFunction andThen(MathFunction afterFunction);
}
