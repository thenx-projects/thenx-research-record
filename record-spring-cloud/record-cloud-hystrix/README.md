# Spring Cloud Hystrix

提供服务降级、服务熔断、线程和信号隔离、请求缓存、请求合并以及服务监控

这里为了做熔断测试，运行起来以后可以断开record-cloud-client-1服务方法做测试，如果这个服务没有挂掉会正常调用，如果挂掉以后会根据我们的熔断等待时间来判断调用fallbackMethod方法
