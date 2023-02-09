package com.techeeresc.tab.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public String hello() {
        return "EC2 test";
    }
}
