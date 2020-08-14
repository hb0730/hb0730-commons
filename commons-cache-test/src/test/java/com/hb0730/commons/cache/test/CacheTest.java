package com.hb0730.commons.cache.test;

import com.hb0730.commons.spring.SpringContextUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author bing_huang
 * @date 2020/07/31 10:19
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CacheTest {
    RedisConnectionFactory factory = null;
    @Autowired
    private WebApplicationContext  ctx;

    @Before
    public void init() {
        SpringContextUtils.setApplicationContext(ctx);
        factory = SpringContextUtils.getBean(RedisConnectionFactory.class);
    }

    @Test
    public void test() {
        System.out.println("test");
    }
}
