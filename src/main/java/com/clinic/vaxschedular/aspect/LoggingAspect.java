package com.clinic.vaxschedular.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.clinic.vaxschedular.Controller.*.*(..))")
    public void AdminControllerMethods() {
    }

    @Pointcut("execution(* com.clinic.vaxschedular.Controller.*.*(..))")
    public void AuthControllerMethods() {
    }

    @Pointcut("execution(* com.clinic.vaxschedular.Controller.*.*(..))")
    public void PatientControllerMethods() {
    }

    @Before("AdminControllerMethods() || AuthControllerMethods() || PatientControllerMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("========================>Method Name: " + methodName);
    }

}