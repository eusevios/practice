package concurrent;

import functions.TabulatedFunction;

public class ReadTask implements Runnable {

    final private TabulatedFunction tabFunc;

    public ReadTask(TabulatedFunction func) {
        this.tabFunc = func;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabFunc.getCount(); i++) {
            synchronized (tabFunc) {
                System.out.printf("After read: i = %d, x = %.3f, y = %.3f %n", i, tabFunc.getX(i), tabFunc.getY(i));
            }
        }
    }
}
