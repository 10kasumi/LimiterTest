package com.cjl.limiter.limiters;

//桶限流
public class BucketLimiter extends Limiter{

    private final long capacity;
    private double remainWater;
    private long lastTime;

    BucketLimiter(int qps){
        super(qps);
        capacity = qps;
        remainWater = capacity;
        lastTime = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        double outWater = ((now - lastTime) / 1000.0) * capacity;   //这段时间匀速流出的水
        lastTime = now;
        if(outWater > remainWater){
            //请求全部处理完成
            remainWater = 1;
            return true;
        } else{
            //还有请求未处理
            remainWater -= outWater;
            //请求不超过桶容量，等到下一次处理
            if(remainWater + 1 <= capacity){
                remainWater += 1;
                return true;
            } else{
                return false;
            }
        }
    }
}
