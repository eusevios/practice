package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.UnitFunction;

import java.util.ArrayList;
import java.util.Iterator;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {

        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);

        ArrayList<Thread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MultiplyingTask task = new MultiplyingTask(func);
            Thread thread = new Thread(task, "Thread-" + i);
            list.add(thread);
        }

        for (Thread thread1 : list) {
            thread1.start();
        }

        while (!list.isEmpty()) {
            Iterator<Thread> iter = list.iterator();
            while (iter.hasNext()) {
                if (!iter.next().isAlive()) iter.remove();
            }
        }

        System.out.println(func);

    }
}
