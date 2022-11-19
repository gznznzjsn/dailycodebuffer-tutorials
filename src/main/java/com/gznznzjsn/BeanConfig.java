package com.gznznzjsn;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.gznznzjsn")
@EnableAspectJAutoProxy
public class BeanConfig {
}
