package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.UnitFunction;

import java.util.ArrayList;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        ArrayList<Thread> list = new ArrayList<>();
        ArrayList<MultiplyingTask> taskList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            MultiplyingTask task = new MultiplyingTask(func);
            taskList.add(task);
            Thread thread = new Thread(task, "Thread-"+i);
            list.add(thread);
        }

        for(Thread thread1: list){
            thread1.start();
        }

        while(!taskList.isEmpty()){
            for (int i = 0; i<taskList.size(); i++){
                if(taskList.get(i).isEnded) taskList.remove(i);
            }
        }

        System.out.println(func);

    }
}
