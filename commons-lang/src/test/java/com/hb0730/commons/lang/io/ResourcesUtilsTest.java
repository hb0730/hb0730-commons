package com.hb0730.commons.lang.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.List;

@Slf4j
public class ResourcesUtilsTest {

    @Test
    public void getResourceTest() {
        try {
            URL url = ResourcesUtils.getResource("sql/test.sql");
            Assert.assertNotNull(url);
            url = ResourcesUtils.getResource("/sql/test.sql");
            Assert.assertNotNull(url);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testGetResourceTest() {
        try {
            //通过Class
            ///file:/E:/IdeaWork/customer-work/hb0730-commons/commons-lang/target/test-classes/sql/test.sql
            URL url = ResourcesUtils.getResource("/sql/test.sql", this.getClass());
            Assert.assertNotNull(url);
            //null
            url = ResourcesUtils.getResource("sql/test.sql", this.getClass());
            Assert.assertNotNull(url);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getResource2Test() {
        try {
            URL url = ResourcesUtils.getResource("sql/test.sql", (ClassLoader) null);
            Assert.assertNotNull(url);
            //通过ClassLoader
            //file:/E:/IdeaWork/customer-work/hb0730-commons/commons-lang/target/test-classes/sql/test.sql
            url = ResourcesUtils.getResource("sql/test.sql", this.getClass().getClassLoader());
            Assert.assertNotNull(url);
            //null
            url = ResourcesUtils.getResource("/sql/test.sql", this.getClass().getClassLoader());
            Assert.assertNotNull(url);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getResourcesTest() {
        try {
            List<URL> resources = ResourcesUtils.getResources("sql");
            Assert.assertNotNull(resources);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testGetResourcesTest() {
        try {
            List<URL> resources = ResourcesUtils.getResources("sql", this.getClass().getClassLoader());
            Assert.assertNotNull(resources);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }
}
