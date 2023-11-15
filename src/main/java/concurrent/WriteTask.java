package concurrent;

import functions.TabulatedFunction;

public class WriteTask implements Runnable {

    final private TabulatedFunction tabFunc;
    final private double value;

    WriteTask(TabulatedFunction func, double val) {
        this.tabFunc = func;
        this.value = val;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabFunc.getCount(); i++) {
            synchronized (tabFunc) {
                tabFunc.setY(i, value);
                System.out.printf("Writing for index %d complete %n", i);
            }
        }
    }
}
