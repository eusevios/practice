package functions;

import ui.Functions;

@Functions(name = "Нулевая функция y=0", priority = 3)
public class ZeroFunction extends ConstantFunction {
    public ZeroFunction() {
        super(0);
    }
}
