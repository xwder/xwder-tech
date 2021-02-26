package com.xwder.example.jsr.controller;

import com.xwder.example.common.result.CommonResult;
import com.xwder.example.jsr.dto.PersonDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * jsr controller
 *
 * @author xwder
 * @date 2021/2/26 10:41
 */
@Validated
@RestController()
@RequestMapping(value = "/jsr")
public class JsrController {

    /**
     * 校验对象
     *
     * @param person
     * @return
     */
    @RequestMapping("/object")
    public CommonResult validateObject(@Valid @NotNull PersonDto person) {
        return CommonResult.success(person);
    }

    /**
     * 校验字符串 非对象型参数需要添加  @Validated
     *
     * @param str
     * @return
     */
    @RequestMapping("/string")
    public CommonResult validateString(@NotEmpty(message = "str不能为空") String str) {

        return CommonResult.success();
    }

}
