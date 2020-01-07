package cn.sang.controller;

import cn.sang.pojo.Dept;
import cn.sang.pojo.Returned;
import cn.sang.pojo.Sys_User;
import cn.sang.pojo.VO.DeptVO;
import cn.sang.service.DeptService;
import cn.sang.utlis.Pull_Down;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DeptService deptService;

    @GetMapping("/getDeptList")
    public Object getDeptList(String name,Integer pageNo,Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("DeptList",deptService.getDeptListByName(name,pageNo,pageSize));
        map.put("DeptLists",Pull_Down.getDeptList(deptService.getDeptVoList()));
        return Returned.ok(map);
    }

    @DeleteMapping("/delDept/{id}")
    public Object DelUser(@PathVariable Integer id){
        if(deptService.DelDept(id)){
            return Returned.ok("删除成功");
        }else{
            return Returned.err("删除失败");
        }
    }

    @PutMapping("/putDept")
    public Object UpdUser(Dept dept){
        if(deptService.UpdDept(dept)){
            return Returned.ok("修改成功");
        }else{
            return Returned.err("修改失败");
        }
    }

    @PostMapping("/insDept")
    public Object InsUser(Dept dept){
        if(deptService.InsDept(dept)){
            return Returned.ok("新增成功");
        }else {
            return Returned.err("新增失败");
        }
    }
}
