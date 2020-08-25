package com.hb0730.commons.spring.test;

import com.hb0730.commons.spring.IpUtils;
import com.hb0730.commons.spring.ServletUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bing_huang
 * @date 2020/07/31 13:30
 * @since V1.0
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/{id}")
    public String getPerson(@PathVariable long id) {
        System.out.println(ServletUtils.getParameter("test"));
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtils.getIp(request);
        Assert.assertNotNull(ip);
        LOGGER.info("ip:{}", ip);
        return "" + id;
    }

}
