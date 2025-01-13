package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * ClassName: AutoFillAspect
 * Package: com.sky.aspect
 * Description:
 * Custom aspect to implement the processing logic for automatic filling of common fields.
 * @Author Jingran Liu
 * @Create 2025/1/12 18:51
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * Pointcut
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut(){}

    /**
     * Before advice, assigning values to common fields in the advice.
     */

    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("Start auto-filling common fields...");

        // 1. Retrieve the type of database operation on the currently intercepted method.

        // (1) Method signature object
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // (2) Obtain the annotation object on the method
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        // (3) Determine the type of database operation
        OperationType operationType = autoFill.value();

        // 2. Retrieve the parameters on the currently intercepted method -- entity object
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }
        // Note: make sure that the first parameter is the entity object
        Object entity = args[0];

        // 3. Prepare the data
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // 4. According to different intercepted method, assign values of fields through reflection
        if(operationType == OperationType.INSERT){

            try {
                // (1) Set values for the 4 common fields.
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                // (2) set values of fields through reflection
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if(operationType == OperationType.UPDATE){
            // Set values for the 2 common fields.
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
