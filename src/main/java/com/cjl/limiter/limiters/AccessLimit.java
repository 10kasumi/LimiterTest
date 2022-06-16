package com.cjl.limiter.limiters;

import com.cjl.limiter.enums.LimiterType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    int qps();
    LimiterType limiterEnum();
}
