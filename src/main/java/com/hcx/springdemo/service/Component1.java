package com.hcx.springdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Title: Component1.java
 * @Package com.hcx.springdemo.service
 * @Description: 发布者
 * @Author: hongcaixia
 * @Date: 2024/5/15 19:48
 * @Version V1.0
 */
@Component
public class Component1 {
    private static final Logger log = LoggerFactory.getLogger(Component1.class);

    @Autowired
    private ApplicationEventPublisher context;

    public void register(){
        log.info("用户注册");
        context.publishEvent(new UserRegisterEvent(this));
    }
}
