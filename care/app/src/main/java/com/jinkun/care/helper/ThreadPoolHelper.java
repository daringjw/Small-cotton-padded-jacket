package com.jinkun.care.helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by coderwjq on 2017/8/21 18:48.
 */

public class ThreadPoolHelper {
    private static ThreadPoolHelper mInstance;
    private ExecutorService mExecutorService;

    private ThreadPoolHelper() {
        mExecutorService = Executors.newCachedThreadPool();
    }

    public static ThreadPoolHelper getInstance() {
        if (mInstance == null) {
            synchronized (ThreadPoolHelper.class) {
                mInstance = new ThreadPoolHelper();
            }
        }
        return mInstance;
    }

    public void execute(Runnable task) {
        mExecutorService.execute(task);
    }
}
