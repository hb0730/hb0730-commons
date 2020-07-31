package com.hb0730.commons.spring.test;

import com.hb0730.commons.spring.ServletUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bing_huang
 * @date 2020/07/31 13:30
 * @since V1.0
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @GetMapping("/{id}")
    public String getPerson(@PathVariable long id) {
        System.out.println(ServletUtils.getParameter("test"));
        return "" + id;
    }

}
