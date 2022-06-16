package com.cjl.limiter.limiters;

import com.cjl.limiter.enums.LimiterType;
import com.cjl.limiter.exception.LimiterException;
import com.cjl.limiter.result.CodeMsg;

public class LimiterFactory {

    public static Limiter getLimiter(LimiterType limiterType, int qps) throws LimiterException {
        switch (limiterType){
            case COUNT_LIMITER:
                return new CountLimiter(qps);
            case BUCKET_LIMITER:
                return new BucketLimiter(qps);
            case TOKEN_BUCKET_LIMITER:
                return new TokenBucketLimiter(qps);
            default:
                throw new LimiterException(CodeMsg.UNKNOWN_LIMITER);
        }
    }
}
