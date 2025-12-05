package com.company.devicemanagement.config;


import com.company.devicemanagement.aspects.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages = "com.company.devicemanagement")
@EnableAspectJAutoProxy
public class ProjectConfig {

  private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

  @Bean
  public LoggingAspect aspect() {
    return new LoggingAspect();
  }

  public void setLogger(Logger logger) {
    this.logger = logger;
  }
}
