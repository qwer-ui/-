package cn.sang.controller;

import cn.sang.pojo.Returned;
import cn.sang.pojo.Sys_Menu;
import cn.sang.pojo.Sys_User;
import cn.sang.pojo.VO.MenuVO;
import cn.sang.service.MenuService;
import cn.sang.service.RoleService;
import cn.sang.utlis.Menu_Down;
import cn.sang.utlis.Pull_Down;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    RoleService roleService;

    @GetMapping("/getMenu")
    public Object getMenuList(Integer UserId){
        List<MenuVO> list = menuService.getMenuByUserId(UserId);
        return Returned.ok(list);
    }

    @GetMapping("/getMenuList")
    public Object getMenuList(String name,Integer pageNo,Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("MenuList",menuService.getMenuListByName(name,pageNo,pageSize));
        map.put("MenuLists",Menu_Down.getDeptList(menuService.getMenuListAll()));
        map.put("RoleList",roleService.getRoleList());
        return Returned.ok(map);
    }

    @DeleteMapping("/delMenu/{id}")
    public Object DelUser(@PathVariable Integer id){
        if(menuService.DelMenu(id)){
            return Returned.ok("删除成功");
        }else{
            return Returned.err("删除失败");
        }
    }

    @PostMapping("/putMenu")
    public Object UpdUser(@RequestBody Sys_Menu sys_menu){
        if(menuService.UpdMenu(sys_menu)){
            return Returned.ok("修改成功");
        }else{
            return Returned.err("修改失败");
        }
    }

    @PostMapping("/insMenu")
    public Object InsUser(@RequestBody Sys_Menu menu){
        if(menuService.InsMenu(menu)){
            return Returned.ok("新增成功");
        }else {
            return Returned.err("新增失败");
        }
    }



}
