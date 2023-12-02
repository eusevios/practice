package ui;

import functions.TabulatedFunction;

public interface Displayable {
    void functionPresentation(TabulatedFunction function);

    void addPoint(double x, double y);

    void removePoint(int index);

    TabulatedFunction getFunc();

}
