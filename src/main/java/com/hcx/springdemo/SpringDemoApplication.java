package com.hcx.springdemo;

import com.hcx.springdemo.service.Component1;
import com.hcx.springdemo.service.UserRegisterEvent;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) throws Exception {
        // 返回spring容器 ApplicationContext的子接口
        ConfigurableApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);
        // 实际是调用的BeanFactory的方法 BeanFactory是ApplicationContext的成员变量
        //context.getBean("aaa");
        /**
         * ApplicationEventPublisher
         */
        //发事件
        //context.publishEvent(new UserRegisterEvent(context)); //订阅者收到事件:com.hcx.springdemo.service.UserRegisterEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@be68757, started on Wed May 15 20:56:12 CST 2024]
        context.getBean(Component1.class).register();


        /**
         * EnvironmentCapable 获取配置信息
         */
        String javaHome = context.getEnvironment().getProperty("java_home");
        System.out.println(javaHome); // D:\JDK\jdk11
        String property = context.getEnvironment().getProperty("server.port");
        System.out.println(property); // 8080


        /**
         * ResourcePatternResolver
         */
        Resource[] resources = context.getResources("classpath*:META-INF/spring.factories");//不带* 没法再jar中找
        for (Resource resource : resources) {
            /**
             * URL [jar:file:/H:/repository/org/springframework/boot/spring-boot/2.7.12/spring-boot-2.7.12.jar!/META-INF/spring.factories]
             * URL [jar:file:/H:/repository/org/springframework/boot/spring-boot-autoconfigure/2.7.12/spring-boot-autoconfigure-2.7.12.jar!/META-INF/spring.factories]
             * URL [jar:file:/H:/repository/org/springframework/spring-beans/5.3.27/spring-beans-5.3.27.jar!/META-INF/spring.factories]
             */
            System.out.println(resource);
        }

        /**
         * MessageSource
         */
        //浏览器的请求头会携带这些语言标识
        System.out.println(context.getMessage("hi",null, Locale.CHINA));
        System.out.println(context.getMessage("hi",null, Locale.ENGLISH));
        System.out.println(context.getMessage("hi",null, Locale.JAPANESE));





        System.out.println(context);

        //因为singletonObjects变量是私有的，通过反射获取
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String,Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);

        /**
         * component1=com.hcx.springdemo.service.Component1@5c723f2d
         * component2=com.hcx.springdemo.service.Component2@432f521f
         */
        map.entrySet().stream().filter(e->e.getKey().startsWith("component")).forEach(e-> System.out.println(e.getKey()+"="+e.getValue()));


    }

}
