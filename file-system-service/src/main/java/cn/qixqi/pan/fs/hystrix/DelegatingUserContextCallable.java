package cn.qixqi.pan.fs.hystrix;

import cn.qixqi.pan.fs.util.UserContext;
import cn.qixqi.pan.fs.util.UserContextHolder;

import java.util.concurrent.Callable;

/**
 * 将 UserContext 注入到 Hystrix命令中
 * @param <V>
 */
public final class DelegatingUserContextCallable<V> implements Callable<V> {

    // Hystrix 命令池传入线程调用的 Callable 类
    private final Callable<V> delegate;

    // 保存来自父线程的 UserContext
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate, UserContext userContext){
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    /**
     * call() 方法在被 Hystrix 保护的方法调用之前调用
     * @return
     * @throws Exception
     */
    public V call() throws Exception {
        // 保存到子线程，即运行 Hystrix 保护的方法的线程
        UserContextHolder.set(originalUserContext);

        try {
            // 调用被 Hystrix 保护的方法
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate, UserContext userContext){
        return new DelegatingUserContextCallable<V> (delegate, userContext);
    }
}
