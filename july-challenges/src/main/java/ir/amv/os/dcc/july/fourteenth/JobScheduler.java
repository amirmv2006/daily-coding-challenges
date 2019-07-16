package ir.amv.os.dcc.july.fourteenth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Good morning! Here's your coding interview problem for today.
 * <p>
 * This problem was asked by Apple.
 * <p>
 * Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
 *
 * @author Amir
 */
public class JobScheduler extends Thread {

    private final PriorityQueue<Task> queue;
    private boolean running;

    public JobScheduler() {
        running = true;
        queue = new PriorityQueue<>(Comparator.comparingLong(Task::getScheduledRun));
        start();
    }

    public void schedule(Runnable runnable, int delayMillis) {
        long scheduledRun = delayMillis + System.currentTimeMillis();
        queue.add(new Task(runnable, scheduledRun));
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                Task task;
                long timeout = 0;
                while ((task = queue.peek()) != null &&
                        (timeout = task.scheduledRun - System.currentTimeMillis()) <= 0) {
                    task.runnable.run();
                    queue.remove(task);
                }
                if (timeout < 0) {
                    timeout = 0;
                }
                synchronized (this) {
                    System.out.println("Waiting for " + timeout + " millis");
                    wait(timeout);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }

    @AllArgsConstructor
    @Data
    private static class Task {
        Runnable runnable;
        long scheduledRun;
    }

    public static void main(String[] args) throws InterruptedException {
        JobScheduler scheduler = new JobScheduler();
        Thread.sleep(1000);
        scheduler.schedule(() -> System.out.println("2 Second"), 2000);
        scheduler.schedule(() -> System.out.println("1 Second"), 1000);
        scheduler.schedule(() -> System.out.println("1.1 Second"), 1100);
        scheduler.schedule(() -> System.out.println("1.2 Second"), 1200);
        scheduler.schedule(() -> System.out.println("0.5 Second"), 500);
    }
}
