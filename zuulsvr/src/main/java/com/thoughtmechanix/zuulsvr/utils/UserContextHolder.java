package com.thoughtmechanix.zuulsvr.utils;

import org.springframework.util.Assert;

/**
 * Created by gongzhaopeng on 27/12/2017.
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext =
            new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context,
                "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    public static UserContext createEmptyContext() {
        return new UserContext();
    }
}
