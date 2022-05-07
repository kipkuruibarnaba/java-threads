package example10;

import java.util.concurrent.*;

/**
 * @author Barnaba Mutai
 * Created on Saturday, May , 07, 2022
 */
/*
 * Checks whether the task has finished or not using Executors
 * When it's finished, print the result
 */

public class CheckIfTaskIsDoneUsingExecutors {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Thread main started");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> result = executorService.submit(new SumFirstNumbers(50));

        while (!result.isDone()) {
            System.out.println("Task is still processing");
            Thread.sleep(500l);
        }

        System.out.println("Task is finished: " + result.isDone());
        System.out.println("Task result is: " + result.get());

        executorService.shutdown();

        System.out.println("Thread main finished");
    }
}

class SumFirstNumbers implements Callable<Integer> {
    private int n;

    public SumFirstNumbers(int n) {
        this.n = n;
    }

    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Adding " + i);
            sum += i;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}

