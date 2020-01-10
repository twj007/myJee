package com.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/***
 * @author info
 * 通过枚举的方式返回结果
 */
@Slf4j
public enum Results {

    SUCCESS(200),
    BAD__REQUEST(500),
    NOt_FOUND(400),
    FORBIDDEN(0),
    TOKEN_EXPIRED(400),
    SOLD_OUT(666),
    TIME_OUT(1000),
    REPEAT_REQUEST(1405),
    USER_EXISTS(400, "该用户已存在"),
    INVALIDATE(400, "格式校验不通过"),
    USER_NOT_EXISTS(400, "该用户不存在"),
    INCURRED_PASSWORD(400, "密码不正确");

    private final int code;

    private String msg;

    Results(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    Results(int code) {
        this.code = code;
    }

    public <T> ResultBody<T> result(String msg, T body){
        return ResultBody.result(code, msg, body);
    }

    public <T> ResultBody<T> result(T body){
        if(StringUtils.isEmpty(msg)){
            log.error("该Enum的msg数据为空，补充为空字符串");
            return ResultBody.result(code, "", body);
        }else{
            return ResultBody.result(code, msg, body);
        }
    }
}
