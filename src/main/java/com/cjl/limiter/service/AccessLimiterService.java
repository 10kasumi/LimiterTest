package com.cjl.limiter.service;

import com.cjl.limiter.enums.LimiterType;
import com.cjl.limiter.exception.LimiterException;
import com.cjl.limiter.limiters.Limiter;
import com.cjl.limiter.limiters.LimiterFactory;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class AccessLimiterService {
    // 计数器实例
    private Limiter countLimiter = LimiterFactory.getLimiter(LimiterType.COUNT_LIMITER, 10);
    // 漏桶实例
    private Limiter bucketLimiter = LimiterFactory.getLimiter(LimiterType.BUCKET_LIMITER, 10);
    // 令牌桶实例
    private Limiter tokenBucketLimiter = LimiterFactory.getLimiter(LimiterType.TOKEN_BUCKET_LIMITER, 10);
    //guava的RateLimiter
    private RateLimiter rateLimiter = RateLimiter.create(10);

    public AccessLimiterService() throws LimiterException {
    }

    // 漏桶算法
    public boolean bucketAcquire() {
        return bucketLimiter.tryAcquire();
    }

    // 计算器算法
    public boolean countAcquire() {
        return countLimiter.tryAcquire();
    }

    //令牌桶算法
    public boolean tokenBucketAcquire() {
        return tokenBucketLimiter.tryAcquire();
    }

    //RateLimiter算法
    public boolean rateAcquire(){
        return rateLimiter.tryAcquire();
    }

}
