package com.cfy.web.controller;

import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
    @GetMapping("/hello")
	public String hello(Locale locale, Model model) {
		return "Hello World";
	}

}