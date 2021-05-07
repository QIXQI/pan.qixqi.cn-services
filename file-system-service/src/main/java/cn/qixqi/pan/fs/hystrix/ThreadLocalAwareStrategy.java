package cn.qixqi.pan.fs.hystrix;

import cn.qixqi.pan.fs.util.UserContextHolder;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义 Hystrix 并发策略类
 */
public class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {

    // Spring Cloud已经定义了一个 HystrixConcurrencyStrategy，用于处理安全
    private HystrixConcurrencyStrategy existingConcurrencyStrategy;

    public ThreadLocalAwareStrategy(HystrixConcurrencyStrategy existingConcurrencyStrategy){
        this.existingConcurrencyStrategy = existingConcurrencyStrategy;
    }

    /**
     * 线程中的等待队列
     * @param maxQueueSize
     * @return
     */
    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize){
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy.getBlockingQueue(maxQueueSize)
                : super.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T>HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv){
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy.getRequestVariable(rv)
                : super.getRequestVariable(rv);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(
            HystrixThreadPoolKey threadPoolKey,
            HystrixProperty<Integer> corePoolSize,
            HystrixProperty<Integer> maximumPoolSize,
            HystrixProperty<Integer> keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue){
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
                : super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * 注入 Callable 实现，设置 UserContext
     * @param callable
     * @param <T>
     * @return
     */
    @Override
    public <T>Callable<T> wrapCallable(Callable<T> callable){
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy.wrapCallable(
                        new DelegatingUserContextCallable<T>(callable, UserContextHolder.get()))
                : super.wrapCallable(
                        new DelegatingUserContextCallable<T>(callable, UserContextHolder.get()));
    }
}
