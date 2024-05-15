package com.hcx.springdemo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



/**
 * @Title: Component1.java
 * @Package com.hcx.springdemo.service
 * @Description: 订阅事件
 * @Author: hongcaixia
 * @Date: 2024/5/15 19:48
 * @Version V1.0
 */
@Component
public class Component2 {

    private static final Logger log = LoggerFactory.getLogger(Component2.class);
    @EventListener
    public void subscribe(UserRegisterEvent event){
        log.info("订阅者收到事件:{}",event);
    }
}
