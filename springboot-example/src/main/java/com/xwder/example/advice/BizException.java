package com.xwder.example.advice;

import com.xwder.example.common.result.ResultCode;
import lombok.Getter;

/**
 * 自定制异常类
 *
 * @author xwder
 * @date 2020年7月9日
 */
@Getter
public class BizException extends RuntimeException {
    private long code;
    private String message;

    public BizException(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(ResultCode resultCodeEnum) {
        this.code = (int) resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
