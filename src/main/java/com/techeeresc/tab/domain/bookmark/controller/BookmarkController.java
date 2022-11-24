package com.techeeresc.tab.domain.bookmark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookmarkController {
    @GetMapping
    public String hello() {
        return "success";
    }
}
