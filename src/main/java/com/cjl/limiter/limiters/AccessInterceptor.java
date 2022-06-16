package com.cjl.limiter.limiters;

import com.cjl.limiter.enums.LimiterType;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //AccessLimit注解处理器
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null){
                return true;
            }
            int qps = accessLimit.qps();
            LimiterType limiterType = accessLimit.limiterEnum();
            Limiter limiter = LimiterFactory.getLimiter(limiterType, qps);
            return limiter.tryAcquire();
        }
        return true;
    }
}
