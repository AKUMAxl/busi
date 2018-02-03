package com.yangchedou.lib_common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 27740 on 2017/9/26.
 */

public class XlThreadPoolUtil {

    public ExecutorService executorService;

    private static XlThreadPoolUtil intance;


    public static XlThreadPoolUtil getIntance(){
        if (intance==null){
            synchronized (Object.class){
                intance = new XlThreadPoolUtil();
            }
        }
        return intance;
    }

    /**
     * @param i 线程池中线程的数量
     * @return  返回一个固定线程数量的线程池，该线程池中的线程数量始终不变
     */
    public ExecutorService getFixedThreadPool(int i){
        return Executors.newFixedThreadPool(i);
    }


    /**
     * @return  返回一个可以根据实际情况调整线程池中线程的数量的线程池
     */
    public ExecutorService getCachedThreadPool(){
        return Executors.newCachedThreadPool();
    }

    /**
     * @return  该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，
     *           多余的任务会保存到一个任务队列中，等待这一个线程空闲，当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务。
     */
    public ExecutorService getSingleThreadExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    /**
     * @param   i   核心线程数一般为CPU数量+1，而最大线程数可以设为CPU的数量*2+1    获取CPU数量的方法:Runtime.getRuntime().availableProcessors();
     * @return  该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池
     */
    public ExecutorService getScheduledThreadPool(int i){
        return Executors.newScheduledThreadPool(i);
    }

    /**
     * @return  该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。
     *          只不过和上面的区别是该线程池大小为1，而上面的可以指定线程池的大小
     */
    public ExecutorService getSingleThreadScheduledExecutor(){
        return Executors.newSingleThreadScheduledExecutor();
    }

}
