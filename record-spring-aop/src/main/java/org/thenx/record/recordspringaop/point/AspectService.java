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

    @Pointcut(value = "execution(* org.thenx..*.*(..))")
    public void pointcut() {
        logger.info("\n-------> 0. 进入 @Pointcut方法");
    }

    /**
     *
     * 第一个进入
     *
     * @param joinPoint join point
     * @return obj
     * @throws Throwable thr
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("\n-------> 1. 进入 @Around 方法" + " ProceedingJoinPoint: " + joinPoint);
        try {
            logger.info("\n-------> try 进入 Around 方法" + " join point: " + joinPoint.proceed());
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            logger.info("\n-------> finally 进入 Around 方法");
        }
    }

    /**
     * 第二个进入
     */
    @Before(value = "pointcut()")
    public void beginTransaction() {
        logger.info("\n-------> 2. 进入 @Before 方法");
    }

    /**
     * 第三个进入
     */
    @After(value = "pointcut()")
    public void commit() {
        logger.info("\n-------> 3. 进入 @After 方法");
    }

    /**
     * 第四个进入
     * @param joinPoint join point
     * @param returnObject obj
     */
    @AfterReturning(value = "pointcut()", returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
        logger.info("\n-------> 4. 进入 @AfterReturning 方法" + " JoinPoint: " + joinPoint + " & returnObject: " + returnObject);
    }

    /**
     * 有异常的时候进入
     */
    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        logger.info("\n-------> 5. 进入 @AfterThrowing 方法，异常可回滚");
    }


}
