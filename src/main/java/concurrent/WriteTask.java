package concurrent;
import functions.TabulatedFunction;

import java.lang.Runnable;
import java.util.Formatter;

public class WriteTask implements Runnable{

    private TabulatedFunction tabFunc;
    private double value;

    WriteTask(TabulatedFunction func, double val){
        this.tabFunc = func;
        this.value = val;
    }

    @Override
    public void run() {
        for(int i = 0; i < tabFunc.getCount(); i++){
            synchronized (this) {
                tabFunc.setY(i, value);
                System.out.printf("Writing for index %d complete %n", i);
            }
        }
    }
}
