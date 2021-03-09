package com.xwder.example.cache.ehcache.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xwder
 * @date 2021/3/8 16:17
 */
@Builder
@Data
public class EhcacheDto {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID 不能为空")
    private Integer ID;
    @Size(max = 33)
    @NotEmpty(message = "name 不能为空")
    private String name;
    private Integer age;

}
