package com.hb0730.commons.spring.test;

import com.hb0730.commons.lang.collection.ArrayUtils;
import com.hb0730.commons.spring.ResourcesUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author bing_huang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ResourcesUtilsTest {

    @Test
    public void getResourcesTest() throws IOException {
        Resource[] resources = ResourcesUtils.getResources("classpath:/test/*.yml");
        int length = ArrayUtils.length(resources);
        Assert.assertEquals(length, 2);
    }

    @Test
    public void testGetResourcesTest() throws IOException {
        Resource[] resources = ResourcesUtils.getResources("classpath:/test/*.yml", SpringTestApplication.class.getClassLoader());
        int length = ArrayUtils.length(resources);
        Assert.assertEquals(length, 2);
    }

    @Test
    public void getResourceTest() {
        Resource resource = ResourcesUtils.getResource("test3.yml");
        Assert.assertNotNull(resource);
    }

    @Test
    public void testGetResourceTest() {
        Resource resource = ResourcesUtils.getResource("test3.yml", SpringTestApplication.class.getClassLoader());
        Assert.assertNotNull(resource);
    }
}
