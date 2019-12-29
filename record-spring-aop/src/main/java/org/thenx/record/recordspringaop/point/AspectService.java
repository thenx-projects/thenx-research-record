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
        logger.info("\n-------> 2. 进入 @Before 方法");
    }

    @After(value = "pointcut()")
    public void commit() {
        logger.info("\n-------> 3. 进入 @After 方法");
    }

    @AfterReturning(value = "pointcut()", returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
        logger.info("\n-------> 4. 进入 @AfterReturning 方法" + " JoinPoint: " + joinPoint + " & returnObject: " + returnObject);
    }

    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        logger.info("\n-------> 5. 进入 @AfterThrowing 方法，异常可回滚");
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("\n-------> 6. 进入 @Around 方法" + " ProceedingJoinPoint: " + joinPoint);
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
