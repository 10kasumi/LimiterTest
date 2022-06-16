package com.cjl.limiter.exception;

import com.cjl.limiter.result.CodeMsg;

//自定义异常
public class LimiterException extends Exception{

    private CodeMsg codeMsg;

    public LimiterException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }
}
