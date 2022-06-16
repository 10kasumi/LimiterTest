package com.cjl.limiter.controller;

import com.cjl.limiter.enums.LimiterType;
import com.cjl.limiter.limiters.AccessLimit;
import com.cjl.limiter.result.CodeMsg;
import com.cjl.limiter.result.Result;
import com.cjl.limiter.service.AccessLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccessLimiterController {
    @Autowired
    private AccessLimiterService accessLimiterService;

    @RequestMapping("/counter")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterType.COUNT_LIMITER)
    public Result counter() {
        if (accessLimiterService.countAcquire()) {
            // （业务逻辑）
            System.out.println("调用了计数器算法");
            return new Result(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new Result(CodeMsg.ACQUIRE_LIMITED);
    }

    @RequestMapping("/bucket")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterType.BUCKET_LIMITER)
    public Result bucket() {
        if (accessLimiterService.bucketAcquire()) {
            System.out.println("调用了漏桶算法");
            // (业务逻辑)
            return new Result(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new Result(CodeMsg.ACQUIRE_LIMITED);
    }

    @RequestMapping("/rateLimiter")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterType.RATE_LIMITER)
    public Result rateLimiter() {
        if (accessLimiterService.rateAcquire()) {
            // （业务逻辑）
            System.out.println("调用了guava的RateLimiter");
            return new Result(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new Result(CodeMsg.ACQUIRE_LIMITED);
    }

    @RequestMapping("/tokenBucket")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterType.TOKEN_BUCKET_LIMITER)
    public Result tokenBucketLimiter(){
        if(accessLimiterService.tokenBucketAcquire()){
            System.out.println("调用了令牌桶算法");
            return new Result(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new Result(CodeMsg.ACQUIRE_LIMITED);
    }
}
