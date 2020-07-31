package com.hb0730.commons.spring.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author bing_huang
 * @date 2020/07/31 13:20
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServletUtilsTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/person/1?test=哈撒")).andReturn();
    }
}
