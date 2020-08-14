package com.hb0730.commons.spring.test;

import com.hb0730.commons.spring.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author bing_huang
 * @date 2020/07/31 11:55
 * @since V1.0
 */
@SpringBootApplication
public class SpringTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringTestApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
    }
}
