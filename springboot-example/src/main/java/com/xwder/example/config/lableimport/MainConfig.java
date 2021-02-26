package com.xwder.example.config.lableimport;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xwder
 * 注解 @Import 是由Spring提供的注解，支持导入普通 java 类，并将其声明成一个bean。  <p>
 * 主要用于将多个分散的 java config 配置类融合成一个更大的 config 类。 <p>
 * 注解 @Import 三种使用方式 <p>
 * 直接导入普通的 Java 类。<p>
 * 配合自定义的 ImportSelector 使用。<p>
 * 配合 ImportBeanDefinitionRegistrar 使用。<p>
 * @date 2021/2/22 14:40
 */
@Import({CommonJavaBean.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig {


}
