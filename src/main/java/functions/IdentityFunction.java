package functions;

import ui.Functions;

@Functions(name = "Тождественная функция y=x", priority = 5)
public class IdentityFunction implements MathFunction {
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {

        return "Класс является реализацией функции f(x)=x, т.е. каждое значение функции равно аргументу функции.";

    }

    @Override
    public boolean equals(Object obj) {

        return this.getClass() == obj.getClass();

    }

    @Override
    public int hashCode() {

        return 640134561;

    }

    @Override
    public IdentityFunction clone() {

        IdentityFunction newFunc = new IdentityFunction();

        return newFunc;

    }


}