package com.xwder.example.common.result;

/**
 * 封装API的错误码
 *
 * @author wande
 * @version 1.0
 * @date 2019/12/25
 */
public interface IErrorCode {
    /**
     * 返回错误码
     *
     * @return
     */
    long getCode();

    /**
     * 返回错误消息
     *
     * @return
     */
    String getMessage();
}
