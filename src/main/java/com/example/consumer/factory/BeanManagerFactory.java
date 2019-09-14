package com.example.consumer.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanManagerFactory implements ApplicationContextAware {

    private static ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       context = applicationContext;
    }

    public static Object get(Class clazz) {
        try {
            String[] names = context.getBeanNamesForType(clazz);
            if (names.length > 0) {
                return context.getBean(names[0]);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
