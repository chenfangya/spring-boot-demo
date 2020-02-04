package com.cfy.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cfy.shiro.enums.PagesEnum;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * 用户查询.
     * @return
     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        return PagesEnum.USERINFO.getPath();
    }

    /**
     * 用户添加;
     * @return
     */
    @GetMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return PagesEnum.USERINFO_ADD.getPath();
    }

    /**
     * 用户删除;
     * @return
     */
    @GetMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return PagesEnum.USERINFO_DEL.getPath();
    }
}