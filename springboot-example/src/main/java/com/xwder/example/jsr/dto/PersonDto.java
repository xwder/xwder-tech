package com.xwder.example.jsr.dto;

import com.xwder.example.jsr.annotion.StartWithValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author xwder
 * @date 2021年2月26日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    @NotNull(message = "classId 不能为空")
    private String classId;

    @Size(max = 33)
    @NotEmpty(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "sex 值不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

    @Email(message = "email 格式不正确")
    @NotEmpty(message = "email 不能为空")
    private String email;

    /**
     * 自定义注解
     */
    @NotEmpty
    @StartWithValidation(message = "Param 'address' must be start with '北京'.", start = "Beijing")
    private String address;
}