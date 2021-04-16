package com.hb0730.commons.lang.io;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class ResourcesUtilsTest {

    @Test
    public void getResourceTest() {
        URL url = ResourcesUtils.getResource("sql/test.sql");
        Assert.assertNotNull(url);
        url = ResourcesUtils.getResource("/sql/test.sql");
        Assert.assertNotNull(url);
    }

    @Test
    public void testGetResourceTest() {
        //通过Class
        ///file:/E:/IdeaWork/customer-work/hb0730-commons/commons-lang/target/test-classes/sql/test.sql
        URL url = ResourcesUtils.getResource("/sql/test.sql", this.getClass());
        Assert.assertNotNull(url);
        //null
        url = ResourcesUtils.getResource("sql/test.sql", this.getClass());
        Assert.assertNotNull(url);
    }

    @Test
    public void getResource2Test() {
        URL url = ResourcesUtils.getResource("sql/test.sql", (ClassLoader) null);
        Assert.assertNotNull(url);
        //通过ClassLoader
        //file:/E:/IdeaWork/customer-work/hb0730-commons/commons-lang/target/test-classes/sql/test.sql
        url = ResourcesUtils.getResource("sql/test.sql", this.getClass().getClassLoader());
        Assert.assertNotNull(url);
        //null
        url = ResourcesUtils.getResource("/sql/test.sql", this.getClass().getClassLoader());
        Assert.assertNotNull(url);
    }

    @Test
    public void getResourcesTest() {
        List<URL> resources = ResourcesUtils.getResources("sql");
        Assert.assertNotNull(resources);
    }

    @Test
    public void testGetResourcesTest() {
        List<URL> resources = ResourcesUtils.getResources("sql", this.getClass().getClassLoader());
        Assert.assertNotNull(resources);
    }
}
