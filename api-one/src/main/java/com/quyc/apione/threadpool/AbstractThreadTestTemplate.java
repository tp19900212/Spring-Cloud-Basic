package com.quyc.apione.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author quyuanchao
 * @date 2019/6/20 23:41
 * <p>Title: </p>
 * <p>Description: </p>
 */
@Slf4j
public abstract class AbstractThreadTestTemplate {

    void doThreadPoolTest(ExecutorService executorService) {
        int num = 0;
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalNum = num;
            if (num++ == 100) {
                break;
            }
            executorService.execute(() -> {
                int i = 0;
                while (i++ < 100) {
                    doSomething();
                }
                log.info(Thread.currentThread().getName() + "完成任务" + finalNum);
            });
        }
        executorService.shutdown();
    }

    private void doSomething() {
        int a = 0;
        for (int i = 0; i < 10000; i++) {
            a += i;
        }
    }
}
