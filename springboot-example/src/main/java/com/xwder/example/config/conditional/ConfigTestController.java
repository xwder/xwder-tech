package com.xwder.example.config.conditional;

import com.xwder.example.common.result.CommonResult;
import com.xwder.example.util.WebSpringBeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xwder
 * @date 2021/3/3 15:18
 */
@RequestMapping(value = "/test/config")
@RestController
public class ConfigTestController {

    @RequestMapping("/conditionalOnProperty")
    public CommonResult testConditionalOnProperty(){
        ConditionBean conditionalOnPropertyConfigBean = (ConditionBean) WebSpringBeanUtils.getBean("ConditionalOnPropertyConfigBean");
        return CommonResult.success(conditionalOnPropertyConfigBean);
    }
}
