package com.thoughtmechanix.authentication.hystrix;

import com.thoughtmechanix.authentication.utils.UserContext;
import com.thoughtmechanix.authentication.utils.UserContextHolder;
import org.springframework.util.Assert;

import java.util.concurrent.Callable;

/**
 * Created by gongzhaopeng on 27/12/2017.
 */
public final class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate,
                                         UserContext userContext) {
        Assert.notNull(delegate, "delegate cannot be null");
        Assert.notNull(userContext, "userContext cannot be null");

        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    public DelegatingUserContextCallable(Callable<V> delegate) {
        this(delegate, UserContextHolder.getContext());
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public String toString() {
        return delegate.toString();
    }

    public static <V> Callable<V> create(Callable<V> delegate,
                                         UserContext userContext) {
        return new DelegatingUserContextCallable<>(delegate, userContext);
    }
}
