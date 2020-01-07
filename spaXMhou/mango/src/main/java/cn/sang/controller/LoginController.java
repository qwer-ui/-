package cn.sang.controller;

import cn.sang.pojo.Returned;
import cn.sang.pojo.Sys_User;
import cn.sang.service.UserService;
import cn.sang.utlis.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    UserService userService;
    @GetMapping("/login")
    public Object loadUserAuth(String name,String password){
        Sys_User sys_user = userService.loadUserByUsername(name);
        if(sys_user==null){
            return Returned.err("用户名或密码输入错误测试合并");
        }
        if(!PasswordUtils.matches(sys_user.getSalt(),password,sys_user.getPassword())){
            return Returned.err("密码输入错误");
        }
        if (sys_user.getStatus() == 0) {
            return Returned.err("账号已被锁定,请联系管理员");
        }
        return Returned.ok(sys_user);
    }
}
