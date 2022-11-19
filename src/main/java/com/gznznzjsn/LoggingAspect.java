package com.gznznzjsn;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.gznznzjsn.ShoppingCart.checkout(..))")
    public void logBefore(JoinPoint joinPoint) {
        String arg = joinPoint.getArgs()[0].toString();
        System.out.println("Logging before with argument " + arg);
    }

    @After("execution(* *.*.*.checkout(..))")
    public void logAfter() {
        System.out.println("Logging after");
    }

    @Pointcut("execution(* com.gznznzjsn.ShoppingCart.quantity(..))")
    public void afterReturningPointCut(){}

    @AfterReturning(pointcut = "afterReturningPointCut()",returning = "retVal")
    public void afterReturning(int retVal){
        System.out.println("After returning " +retVal);
    }
}
