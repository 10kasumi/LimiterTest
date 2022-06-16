package com.cjl.limiter.result;

import lombok.Data;

@Data
public class CodeMsg {
    private int code;
    private String msg;
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg ACQUIRE_SUCCESS = new CodeMsg(2000, "acquire success!");
    public static CodeMsg UNKNOWN_LIMITER = new CodeMsg(5001, "unknown limiter!");
    public static CodeMsg ACQUIRE_LIMITED = new CodeMsg(5002, "Reject！ Be limited!");

    private CodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg() {
    }
}
