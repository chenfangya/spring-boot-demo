package com.cfy.shiro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfy.shiro.entity.LoginResult;
import com.cfy.shiro.enums.PagesEnum;
import com.cfy.shiro.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    LoginService loginService;

    @GetMapping({"/","/index"})
    public String index(){
        return PagesEnum.INDEX.getPath();
    }

    @GetMapping(value = "/login")
    public String toLogin(Map<String, Object> map,HttpServletRequest request)
    {
        loginService.logout();
        return PagesEnum.LOGIN.getPath();
    }

    @PostMapping(value = "/login")
    public String login(Map<String, Object> map,HttpServletRequest request) throws Exception{
        log.info("login()");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        LoginResult loginResult = loginService.login(userName,password);
        if(loginResult.isLogin()) {
            return "/index";
        }
        else {
            map.put("msg",loginResult.getResult());
            map.put("userName",userName);
            return PagesEnum.LOGIN.getPath();
        }
    }

    @PostMapping(value = "/loginaa")
    public Object loginaa(@RequestParam String username,@RequestParam String password) throws Exception {
        log.info("HomeController.loginaa()");
        SecurityUtils.getSubject().logout();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Map<String, Object> map = new HashMap<>();
        try {
            subject.login(token);
            String sessionid = (String) subject.getSession().getId();
            log.info(String.format("sessionid====== %s", sessionid));
            map.put("msg", sessionid);
            return PagesEnum.INDEX.getPath();

        } catch (AuthenticationException e) {
          String msg= "用户名或者密码不正确";
            map.put("msg", msg);
            // 此方法不处理登录成功,由shiro进行处理
            return PagesEnum.LOGIN.getPath();
        }
    }

    /**
     * 被踢出后跳转的页面
     * @return
     */
    @GetMapping(value = "/kickout")
    public String kickOut() {
        return PagesEnum.KICK_OUT_2.getPath();
    }

    @GetMapping("/403")
    public String unauthorizedRole(){
        log.info("------没有权限-------");
        return PagesEnum.PAGE_403.getPath();
    }

}