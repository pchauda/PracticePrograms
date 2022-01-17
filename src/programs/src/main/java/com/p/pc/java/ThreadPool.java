package com.p.pc.java;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    public static void main(String[] args) {
        int threadCount = 5;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadCount);
        for(int i=0; i < 50; i++) {
            executor.submitTask(new Task(i));
        }
        executor.shutDown();
    }

    static class ThreadPoolExecutor {
        int threadCount;
        LinkedBlockingQueue<Task> queue;
        Worker[] threads;

        ThreadPoolExecutor(int threadCount) {
            this.threadCount = threadCount;
            this.queue = new LinkedBlockingQueue<>();
            this.threads = new Worker[threadCount];

            for(int i=0; i<threadCount; i++) {
                threads[i] = new Worker(i);
                threads[i].start();
            }
        }

        void submitTask(Task task) {
            synchronized (queue) {
                queue.add(task);
                queue.notifyAll();
            }
        }

        void shutDown() {
            while(!queue.isEmpty()) {}
            synchronized (queue) {
                for(Worker worker : threads) {
                    worker.shutDown = true;
                }
                queue.notifyAll();
                try {
                    Thread.sleep(1000); // sleep for two seconds to allow completion of threads
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        class Worker extends Thread {
            int id;
            boolean shutDown = false;

            Worker(int id) {
                this.id = id;
            }
            public void run() {
                while(true) {
                    if(shutDown) break;
                    synchronized (queue) {
                        while(queue.isEmpty() && !shutDown) {
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Task t = queue.poll();
                    try {
                        if(t != null) {
                            t.run();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    static class Task implements Runnable {
        int taskId;

        Task(int taskId) {
            this.taskId = taskId;
        }

        public void run() {
            System.out.println("Executing task : " + taskId);
        }
    }


}
