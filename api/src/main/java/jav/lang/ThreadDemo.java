package jav.lang;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: key=线程池
 * @Author
 * @Date 2020/11/10
 * @Version 1.0
 */
public class ThreadDemo {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 6;//4,6  6,6  6,8  5,7  5,5  6,10
        long keepAliveTime = 0L;
        TimeUnit unit = TimeUnit.SECONDS; // timeunit 时间单位
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();


        /*ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());*/

        /*ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(50), new ThreadPoolExecutor.CallerRunsPolicy());*/

        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(20), new ThreadPoolExecutor.CallerRunsPolicy());

        /*ThreadPoolExecutor executor = new ThreadPoolExecutor(5, Integer.MAX_VALUE,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), threadFactory);*/

        // executor.prestartAllCoreThreads(); // 预启动所有核心线程
        for (int i = 0; i < 310; i++) {
            executor.execute(new MyTask(String.valueOf(i + 1)));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyTask implements Runnable {
    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(toString() + " is running");
        try {
            Thread.sleep(3000); // 模拟处理过程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MyTask[name=" + name + "]";
    }
}

class NameTreadFactory implements ThreadFactory {

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
        System.out.println(t.getName() + " has been created");
        return t;
    }
}

class MyIgnorePolicy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        doLog(r, executor);
    }

    private void doLog(Runnable r, ThreadPoolExecutor e) {
        // 可做日志记录等
        System.err.println(r.toString() + " rejected");
        //System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
    }
}

// question 所以引入AtomicInteger类
// 高并发的情况下，i++无法保证原子性，往往会出现问题，所以引入AtomicInteger类
// keepAliveTime 该参数默认对核心线程无效
// LinkedBlockingQueue（无界阻塞队列），队列最大值为Integer.MAX_VALUE。如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。因为队列很大，很有可能在拒绝策略前，内存溢出。是其劣势；

// 线程线程1-4先占满了核心线程和最大线程数量，然后5、6线程进入等待队列，7-10线程被直接忽略拒绝执行，等1-4线程中有线程执行完后通知5、6线程继续执行。
