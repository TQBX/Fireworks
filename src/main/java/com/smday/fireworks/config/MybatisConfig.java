package com.smday.fireworks.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 * created by Summer-day
 */

@Configuration
@MapperScan(basePackages={"com.smday.fireworks.mbg.mapper","com.smday.fireworks.dao"})
public class MybatisConfig {
}
