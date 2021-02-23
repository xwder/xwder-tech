package com.xwder.example.springboot.config.lableimport;

import com.xwder.example.springboot.config.lableimport.CommonJavaBeanTwo;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 注解 @Import 第三种方式：配合 ImportBeanDefinitionRegistrar 使用 <p>
 *     第1步：创建普通 Java 类 CommonJavaBeanTwo。 <p>
 *     第2步：创建 ImportBeanDefinitionRegistrar 实现类，实现方法直接手动注册一个名叫 commonJavaBeanTwo 的 Bean 到 IOC 容器中。<p>
 *     第3步：创建配置类，导入 MyImportBeanDefinitionRegistrar 类。<p>
 * @author xwder
 * @date 2021/2/22 15:10
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(CommonJavaBeanTwo.class);
        // 注册一个名字叫做 rectangle 的 bean
        beanDefinitionRegistry.registerBeanDefinition("commonJavaBeanTwo", rootBeanDefinition);
    }

}