package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {

  static ApplicationContext ctx;
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }

  public static ApplicationContext getCtx(){
    return ctx;
  }

}
