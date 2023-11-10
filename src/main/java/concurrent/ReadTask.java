package concurrent;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.lang.Runnable;
import java.util.Formatter;
public class ReadTask implements Runnable{

    private TabulatedFunction tabFunc;

    public ReadTask(TabulatedFunction func){
        this.tabFunc = func;
    }

    @Override
    public void run() {
        for(int i = 0; i < tabFunc.getCount(); i++){
            synchronized (this){
                System.out.printf("After read: i = %d, x = %.3f, y = %.3f %n", i, tabFunc.getX(i), tabFunc.getY(i));
            }
        }
    }
}
