package com.quyc.apione.threadpool;

import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author quyuanchao
 * @date 2019/6/20 22:58
 * <p>Title: 测试 ThreadPool</p>
 * <p>Description: </p>
 */
@Slf4j
@RestController
@RequestMapping("threadPool")
public class ThreadPool extends AbstractThreadTestTemplate {

    public static void main(String[] args) {

    }

    /**
     * 无界队列
     * 1.037
     */
    @RequestMapping("/noLimit")
    public void noLimit() {
        ExecutorService noLimit = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new NamedThreadFactory("No-Limit-Thread"));
        doThreadPoolTest(noLimit);
    }

    /**
     * 有界队列
     * 1.024
     */
    @RequestMapping("/limit")
    public void limit() {
        ExecutorService limit = new ThreadPoolExecutor(5, 10, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new NamedThreadFactory("Limit-Thread"));
        doThreadPoolTest(limit);
    }

    /**
     * 多核心线程
     *
     * @param coreSize    the core size
     * @param maxiMumSize the maxi mum size
     */
    @RequestMapping("/bigCoreSize")
    public void bigCoreSize(int coreSize,int maxiMumSize) {
        ExecutorService limit = new ThreadPoolExecutor(coreSize, maxiMumSize, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new NamedThreadFactory("BigCoreSize-Thread"));
        doThreadPoolTest(limit);
    }

}
