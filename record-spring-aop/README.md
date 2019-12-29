# Spring Boot AOP

我们可以用AOP做日志源，最终交给ES作处理

如果一切正常没有意外，那么AOP的执行顺序是：

* 1.进入 @Around 方法 ProceedingJoinPoint: execution(String org.thenx.record.recordspringaop.controller.AopController.aop(Integer))
* 2.进入 @Before 方法
    * 2.1. try 进入 Around 方法 join point: 返回接口
    * 2.2. 进入 @Before 方法
* 3.finally 进入 Around 方法
* 4.进入 @After 方法
* 5.进入 @AfterReturning 方法 JoinPoint: execution(String org.thenx.record.recordspringaop.controller.AopController.aop(Integer)) & returnObject: 返回接口

如果捕捉到异常，那么执行顺序是：

* 1.进入 @Around 方法 ProceedingJoinPoint: execution(String org.thenx.record.recordspringaop.controller.AopController.aop(Integer))
* 2.进入 @Before 方法
* 3.finally 进入 Around 方法
* 4.进入 @After 方法
* 5.进入 @AfterThrowing 方法，异常可回滚 java.lang.RuntimeException: 传入参数不为 1

我们可以看到，所有顺序依次是：
around > before > around > after > afterReturning