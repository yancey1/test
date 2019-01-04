package com.joseph.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:dubbo.properties")
@ImportResource(value = {"classpath:spring/spring-dubbo.xml"})
public class DubboConfig {
}
