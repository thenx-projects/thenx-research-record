package org.thenx.record.recordspringbootredis.service;

/**
 * @author May
 * <p>
 * 具体实现类
 */
@FunctionalInterface
public interface RedisService {

    /**
     * 具体Redis实现
     *
     * @param id id
     * @return str
     */
    String resp(Integer id);
}
