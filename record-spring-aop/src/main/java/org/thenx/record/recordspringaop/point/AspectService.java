package org.thenx.record.recordspringaop.point;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author May
 * <p>
 * AOP 实现类
 */
@Aspect
@Component
public class AspectService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "execution(* org.thenx.record.recordspringaop.controller..*.*(..))")
    public void pointcut() {
        logger.info("\n-------> 1. 进入 @Pointcut方法");
    }

    @Before(value = "pointcut()")
    public void beginTransaction() {
        System.out.println("before beginTransaction");
    }

    @After(value = "pointcut()")
    public void commit() {
        System.out.println("after commit");
    }

    @AfterReturning(value = "pointcut()", returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
        System.out.println("afterReturning");
    }

    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing afterThrowing  rollback");
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("around");
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("around");
        }
    }
}
