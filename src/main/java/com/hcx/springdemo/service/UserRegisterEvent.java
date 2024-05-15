package com.hcx.springdemo.service;

import org.springframework.context.ApplicationEvent;

/**
 * @Title: UserRegisterEvent.java
 * @Package com.hcx.springdemo.service
 * @Description: 用户注册事件
 * @Author: hongcaixia
 * @Date: 2024/5/15 20:47
 * @Version V1.0
 */
public class UserRegisterEvent extends ApplicationEvent {
    public UserRegisterEvent(Object source) {
        super(source);
    }
}
