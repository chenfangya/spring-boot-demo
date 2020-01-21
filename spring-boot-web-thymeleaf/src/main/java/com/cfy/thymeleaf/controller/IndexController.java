package com.cfy.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cfy.thymeleaf.enums.PagesEnum;

/**
*@author    created by ChenFangYa
*@date  2020年1月21日---下午4:24:10
*@action
*/
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return PagesEnum.INDEX.getPath();
    }

    @GetMapping("/fragment")
    public String fragment() {
        return PagesEnum.FRAGMENT.getPath();
    }

    @GetMapping("/layout")
    public String layout() {
        return PagesEnum.LAYOUT.getPath();
    }

    @GetMapping("/home")
    public String home() {
        return PagesEnum.HOME.getPath();
    }
}
