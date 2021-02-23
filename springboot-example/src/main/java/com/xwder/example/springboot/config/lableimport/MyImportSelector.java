package com.xwder.example.springboot.config.lableimport;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 注解 @Import 第二种方式：配合自定义的 ImportSelector 使用
 *      第1步：创建普通 Java 类 ImportSelectorClass。 <p>
 *      第2步：创建 ImportSelector 实现类，selectImports 方法返回 ImportSelectorClass 的全类名。 <p>
 *      第3步：创建配置类，在原来的基础上还导入了 MyImportSelector。   @Import({CommonJavaBean.class,MyImportSelector.class}) <p>
 *
 * @author xwder
 * @date 2021/2/22 15:01
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.xwder.example.springboot.config.lableimport.ImportSelectorClass"};
    }
}
