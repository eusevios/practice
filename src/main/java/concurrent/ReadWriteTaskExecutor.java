package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {

        ConstantFunction source = new ConstantFunction(-1);
        TabulatedFunction listFunc = new LinkedListTabulatedFunction(source, 1 ,100, 100);

        Thread thread1 = new Thread(new ReadTask(listFunc));
        Thread thread2 = new Thread(new WriteTask(listFunc, 0.5));

        thread2.start();
        thread1.start();
    }
}
