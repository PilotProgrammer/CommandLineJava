package com.pilotprogrammer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.pilotprogrammer.beans.MyAnnotationConfigBean;

@Configuration
@PropertySource(value = {"file:${appHome}/config.properties" }) 
@ImportResource("classpath:config/spring/applicationContext.xml")
@ComponentScan("com.pilotprogrammer")
public class Config {
	
   @Bean 
   public MyAnnotationConfigBean helloWorld() {
      return new MyAnnotationConfigBean();
   }
}