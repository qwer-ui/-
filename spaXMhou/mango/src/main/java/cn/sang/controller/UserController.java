package cn.sang.controller;

import cn.sang.pojo.Page;
import cn.sang.pojo.Returned;
import cn.sang.pojo.Sys_User;
import cn.sang.service.DeptService;
import cn.sang.service.RoleService;
import cn.sang.service.UserService;
import cn.sang.utlis.Pull_Down;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    DeptService deptService;

    @Autowired
    RoleService roleService;

    @GetMapping("/getUserList")
    public Object getUserList(String name,Integer pageNo,Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("UserList",userService.getSysUserList(pageNo,pageSize,name));
        map.put("DeptList",Pull_Down.getDeptList(deptService.getDeptVoList()));
        map.put("RoleList",roleService.getRoleList());
       return Returned.ok(map);
    }

    @DeleteMapping("/delUser/{id}")
    public Object DelUser(@PathVariable Integer id){
        if(userService.DelUser(id)){
            return Returned.ok("删除成功");
        }else{
            return Returned.err("删除失败");
        }
    }

    @PutMapping("/putUser")
    public Object UpdUser(Sys_User sys_user){
        if(userService.UpdUser(sys_user)){
            return Returned.ok("修改成功");
        }else{
            return Returned.err("修改失败");
        }
    }

    @PostMapping("/insUser")
    public Object InsUser(Sys_User sys_user){
        if(userService.InsUser(sys_user)){
            return Returned.ok("新增成功");
        }else {
            return Returned.err("新增失败");
        }
    }
}
