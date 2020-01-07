package cn.sang.controller;

import cn.sang.pojo.Returned;
import cn.sang.pojo.Sys_Role;
import cn.sang.pojo.Sys_User;
import cn.sang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/getRoleList")
    public Object getRoleList(String name,Integer pageNo,Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("RoleList",roleService.getRoleListByName(name,pageNo,pageSize));
        map.put("totalRecords",roleService.getRoleCountByName(name));
        return Returned.ok(map);
    }

    @DeleteMapping("/delRole/{id}")
    public Object DelRole(@PathVariable Integer id){
        if(roleService.DelRole(id)){
            return Returned.ok("删除成功");
        }else{
            return Returned.err("删除失败");
        }
    }

    @PutMapping("/putRole")
    public Object UpdRole(Sys_Role sys_role){
        if(roleService.UpdRole(sys_role)){
            return Returned.ok("修改成功");
        }else{
            return Returned.err("修改失败");
        }
    }

    @PostMapping("/insRole")
    public Object InsRole(Sys_Role sys_role){
        if(roleService.InsRole(sys_role)){
            return Returned.ok("新增成功");
        }else {
            return Returned.err("新增失败");
        }
    }
}
