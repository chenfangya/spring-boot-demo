package com.cfy.web.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cfy.web.utils.WebProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProPertiesTest {

	@Autowired
    private WebProperties webProperties;

    @Test
    public void getHello() throws Exception {
    	System.out.println(webProperties.getTitle());
        Assert.assertEquals("ChenFangYa", webProperties.getTitle());
        Assert.assertEquals("滴滴答答滴滴答答", webProperties.getDescription());
    }

}