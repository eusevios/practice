package functions;

import ui.Functions;

@Functions(name = "Единичная функция y=1", priority = 0)
public class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1);
    }
}
