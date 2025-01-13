package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: AutoFill
 * Package: com.sky.annotation
 * Description:
 * Custom annotation used to identify methods that require common field filling.
 * @Author Jingran Liu
 * @Create 2025/1/12 18:36
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // Specify the database operation type: INSERT, UPDATE
    OperationType value();
}
