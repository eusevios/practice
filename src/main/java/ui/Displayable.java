package ui;

import functions.TabulatedFunction;

public interface Displayable {
    void functionPresentation(TabulatedFunction function);

    void addPoint(double x, double y);

    void removePoint(double x);

    TabulatedFunction getFunc();

}
