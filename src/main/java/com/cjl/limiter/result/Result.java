package com.cjl.limiter.result;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg codeMsg){
        if(codeMsg == null) return;
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }


}
