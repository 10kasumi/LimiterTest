package com.cjl.limiter.limiters;

//令牌桶
public class TokenBucketLimiter extends Limiter{

    final int capacity;
    int curTokenNum;
    long lastTime;

    TokenBucketLimiter(int qps){
        super(qps);
        capacity = qps;
        curTokenNum = 0;
        lastTime = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        int intoToken = (int) ((now - lastTime) / 1000.0 * capacity);
        lastTime = now;
        if(intoToken + curTokenNum > capacity){
            //令牌满
            curTokenNum = capacity - 1;
            return true;
        } else if(intoToken + curTokenNum >= 1){
            //还有令牌
            curTokenNum = intoToken + curTokenNum - 1;
            return true;
        } else{
            return false;
        }
    }
}
