package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
    @Before("execution(* com.library.service.*.*(..))")
    public void beforeServiceCall(JoinPoint joinPoint) {
        System.out.println("Starting service method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.library.service.*.*(..))", returning = "result")
    public void afterSuccessfulServiceCall(Object result) {
        System.out.println("Completed successfully with result: " + result);
        System.out.println("Transaction committed");
    }
}
