package com.hb0730.commons.http.test.controller;

import com.hb0730.commons.http.test.model.TestVO;
import org.springframework.web.bind.annotation.*;

/**
 * @author bing_huang
 * @date 2020/08/06 9:30
 * @since V1.0
 */
@RestController
@RequestMapping
public class TestController {
    @GetMapping("/")
    public String get() {
        return "成功";
    }

    @GetMapping("/params")
    public String get(@RequestParam("name") String name) {
        return name;
    }

    @PostMapping("/post")
    public String post() {
        return "成功";
    }

    @PostMapping("/post/params")
    public String post(String name) {
        return name;
    }

    @PostMapping("/post/params/json")
    public String post(@RequestBody TestVO vo) {
        return vo.getId() + "_" + vo.getName();
    }
}
